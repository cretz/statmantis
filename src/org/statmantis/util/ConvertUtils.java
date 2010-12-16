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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.common.primitives.Primitives;

/**
 * Utilities for conversion
 *
 * @author Chad Retz
 */
public class ConvertUtils {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T convertKnownType(Object one, Class<T> to) {
        //let's be lazy right now
        if (to.isPrimitive()) {
            to = Primitives.wrap(to);
        }
        if (one == null) {
            return null;
        } else if (Number.class.isAssignableFrom(to)) {
            try {
                return to.getConstructor(String.class).newInstance(one.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (to.equals(String.class)) {
            return (T) one.toString();
        } else if (to.isEnum()) {
            return (T) Enum.valueOf((Class<Enum>) to, one.toString());
        } else if (java.sql.Date.class.equals(to)) {
            //meh
            try {
                return (T) new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").
                        parse(one.toString()).getTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Unknown class: " + to);
        }
    }
    
    private ConvertUtils() {
    }
}
