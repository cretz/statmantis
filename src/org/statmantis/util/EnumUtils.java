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

import java.util.HashMap;
import java.util.Map;

import org.statmantis.BaseRuntimeException;
import org.statmantis.model.RetrosheetModel;

/**
 * Utilities for enumerates
 * 
 * @author Chad Retz
 */
public final class EnumUtils {

    /**
     * Maps all enumerates of the given class w/ a getRetroId() method
     * 
     * @param <T>
     * @param enumClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T> & RetrosheetModel> Map<String, T> mapEnumerates(
            Class<T> enumClass) {
        T[] values;
        try {
            values = (T[]) enumClass.getMethod("values").invoke(null);
        } catch (Exception e) {
            throw new BaseRuntimeException(e);
        }
        Map<String, T> map = new HashMap<String, T>();
        for (T value : values) {
            for (String id : value.getRetroId().split(",")) {
                map.put(id, value);
            }
        }
        return map;
    }
    
    private EnumUtils() {
    }
}
