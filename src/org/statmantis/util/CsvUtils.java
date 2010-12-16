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
package org.statmantis.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statmantis.BaseRuntimeException;


import au.com.bytecode.opencsv.CSVReader;

/**
 * Utilities for CSV files
 * 
 * @author Chad Retz
 */
public final class CsvUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);
    
    /**
     * Read an entire CSV
     * 
     * @param reader
     * @param skipFirstLine
     * @return
     * @throws IOException
     */
    public static List<String[]> readCsv(Reader reader, 
            boolean skipFirstLine) throws IOException {
        return new CSVReader(reader).readAll();
    }
    
    /**
     * Loads a CSV into a map keyed by enum (0-based column 
     * index is the enum ordinal)
     * 
     * @param <T>
     * @param enumClass
     * @param stream
     * @param skipFirstLine Whether or not to skip the first line
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> List<Map<T, String>> readCsv(
            Class<T> enumClass, Reader reader, boolean skipFirstLine) 
            throws IOException {
        T[] enumValues;
        try {
            enumValues = (T[]) enumClass.getMethod("values").invoke(null);
        } catch (Exception e) {
            throw new BaseRuntimeException(e);
        }
        CSVReader csvReader = new CSVReader(reader);
        List<Map<T, String>> ret = new ArrayList<Map<T, String>>();
        String[] line;
        if (skipFirstLine) {
            csvReader.readNext();
        }
        while ((line = csvReader.readNext()) != null) {
            assert enumValues.length >= line.length : 
                "Value count [" + line.length + "] greater than max [" +
                enumValues.length + "]";
            try {
                Map<T, String> map = new HashMap<T, String>(line.length);
                for (int i = 0; i < line.length; i++) {
                    map.put(enumValues[i], line[i]);
                }
                ret.add(map);
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to read line: " + Arrays.toString(line), e);
                }
            }
        }
        return ret;
    }
    
    private CsvUtils() {
    }
}
