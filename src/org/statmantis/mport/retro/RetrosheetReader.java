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
package org.statmantis.mport.retro;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.statmantis.BaseRuntimeException;

/**
 * Non-thread-safe reader
 * 
 * @author Chad Retz
 * @param <T>
 */
public abstract class RetrosheetReader<T> {

    private static final String DATE_FORMAT = "MM/dd/yyyy";
    
    protected static List<String> splitAndTrimOrNull(String values) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        String[] split = values.split(";");
        List<String> ret = new ArrayList<String>(split.length);
        for (String value : values.split(";")) {
            ret.add(value.trim());
        }
        return ret;
    }
    
    private DateFormat dateFormatter;
    
    protected synchronized DateFormat getDateFormatter() {
        if (dateFormatter == null) {
            dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        }
        return dateFormatter;
    }
    
    protected Date formatSqlDateOrNull(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            try {
                return new Date(getDateFormatter().parse(value).getTime());
            } catch (ParseException e) {
                throw new BaseRuntimeException(e);
            }
        }
    }
    
    public abstract T read() throws IOException;

    protected abstract T read(InputStream stream) throws IOException;
}
