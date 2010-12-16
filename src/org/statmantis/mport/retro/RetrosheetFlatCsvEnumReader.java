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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections15.Transformer;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.statmantis.model.RetrosheetModel;
import org.statmantis.util.CollectionUtils;
import org.statmantis.util.CsvUtils;

/**
 * Base reader for simple CSVs
 * 
 * @author Chad Retz
 *
 * @param <T>
 * @param <U>
 */
public abstract class RetrosheetFlatCsvEnumReader<T extends RetrosheetModel, U extends Enum<U>>
        extends RetrosheetReader<Map<String, T>> {

    /**
     * If this is read directly from a URL, get the URL to read
     * 
     * @return
     */
    protected String getUrl() {
        //no-op
        return null;
    }
    
    /**
     * The enumerate to map the CSV columns
     * 
     * @return
     */
    protected abstract Class<U> getEnumClass();
    
    /**
     * Return whether or not the first line is skipped
     * because it contains column names
     * 
     * @return
     */
    protected abstract boolean isFirstLineSkipped();
    
    /**
     * Get the transformer to build the objects
     * 
     * @return
     */
    protected abstract Transformer<Map<U, String>, Entry<String, T>>
            getTransformer();

    @Override
    public Map<String, T> read() throws IOException {
        HttpClient client = new DefaultHttpClient();
        return read(new ByteArrayInputStream(client.execute(
                new HttpGet(getUrl()), new BasicResponseHandler()).getBytes()));
    }

    @Override
    protected Map<String, T> read(InputStream stream) throws IOException {
        List<Map<U, String>> values = CsvUtils.readCsv(
                getEnumClass(), new InputStreamReader(stream), isFirstLineSkipped());
        List<Entry<String, T>> transformed = CollectionUtils.transform(values, getTransformer());
        Map<String, T> ret = new HashMap<String, T>(values.size());
        for (Entry<String, T> entry : transformed) {
            ret.put(entry.getKey(), entry.getValue());
        }
        return ret;
    }
}
