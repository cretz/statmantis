/*
 * Copyright 2010 Chad Retz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.statmantis.mport.retro.person;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statmantis.model.Person;
import org.statmantis.mport.retro.RetrosheetReader;

/**
 * Person file reader
 *
 * @author Chad Retz
 */
public class RetrosheetPersonReader extends RetrosheetReader<Map<String, Person>> {

    private static final Logger logger = LoggerFactory.getLogger(RetrosheetPersonReader.class);
    
    @Override
    public Map<String, Person> read() throws IOException {
        //assuming this is still the path
        HttpClient client = new DefaultHttpClient();
        HttpEntity entity = client.execute(new HttpGet(
                "http://www.retrosheet.org/retroID.htm")).getEntity();
        if (entity == null) {
            return null;
        } else {
            return read(new BufferedInputStream(entity.getContent()));
        }
    }

    @Override
    protected Map<String, Person> read(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            String line;
            //skip until players begin
            do {
                line = reader.readLine();
            } while (!line.contains("LAST, FIRST, ID, DEBUT"));
            //get players
            Map<String, Person> ret = new HashMap<String, Person>();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            do {
                line = reader.readLine().trim();
                if (line.contains("</pre>")) {
                    break;
                } else if(!line.isEmpty()) {
                    try {
                        String[] pieces = line.trim().split(",");
                        Person person = new Person();
                        person.setLastName(pieces[0].trim());
                        person.setFirstName(pieces[1].trim());
                        person.setRetroId(pieces[2].trim());
                        //may not have a debut date
                        if (pieces.length > 3) {
                            try {
                                person.setDebut(new Date(dateFormat.parse(pieces[3].trim()).getTime()));
                            } catch (ParseException e) {
                                if (logger.isWarnEnabled()) {
                                    logger.warn("Unable to read date '" + pieces[3].trim() +
                                            "' in line: " + line.trim());
                                }
                            }
                        }
                        if (ret.put(person.getRetroId(), person) != null && logger.isWarnEnabled()) {
                            logger.warn("There were two records for " + person.getRetroId());
                        }
                    } catch (Exception e) {
                        logger.error("Unable to read line: " + line, e);
                    }
                }
            } while (true);
            return ret;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                //no-op
            }
        }
    }

}
