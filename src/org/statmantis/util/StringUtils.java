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

/**
 * Utilities for strings
 *
 * @author Chad Retz
 */
public class StringUtils {

    public static String capitalize(String string) {
        char begin = Character.toUpperCase(string.charAt(0));
        if (string.length() == 1) {
            return Character.toString(begin);
        } else {
            return begin + string.substring(1);
        }
    }
    
    public static String toCamelCase(String string) {
        char begin = Character.toLowerCase(string.charAt(0));
        if (string.length() == 1) {
            return Character.toString(begin);
        } else {
            return begin + string.substring(1);
        }
    }
    
    private StringUtils() {
    }
}
