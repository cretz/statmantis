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
import java.util.Map.Entry;

import org.apache.commons.collections15.Transformer;

/**
 * Extension of {@link org.apache.commons.collections15.MapUtils}
 * 
 * @author Chad Retz
 */
public final class MapUtils extends org.apache.commons.collections15.MapUtils {
    
    public static <T, U> Entry<T, U> createEntry(final T key, final U value) {
        return new Entry<T, U>() {
            private U entryValue = value;
            @Override
            public T getKey() {
                return key;
            }

            @Override
            public U getValue() {
                return entryValue;
            }

            @Override
            public U setValue(U entryValue) {
                this.entryValue = entryValue;
                return entryValue;
            }
        };
    }
    
    public static <T, U, V, W> Map<V, W> transform(Map<T, U> original,
            Transformer<Entry<T, U>, Entry<V, W>> transformer) {
        Map<V, W> ret = new HashMap<V, W>(original.size());
        //TODO: Fail, can't add to an entry set idiot!
        ret.entrySet().addAll(CollectionUtils.transform(
                original.entrySet(), transformer));
        return ret;
    }
    
    private MapUtils() {
    }
}
