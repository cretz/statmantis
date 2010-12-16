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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.collections15.Transformer;
import org.statmantis.BaseRuntimeException;
import org.statmantis.mport.retro.RetrosheetRuntimeException;

/**
 * Extension of {@link org.apache.commons.collections15.CollectionUtils}
 * 
 * @author Chad Retz
 */
public final class CollectionUtils extends org.apache.commons.collections15.CollectionUtils {

    public static <T, U, V extends Collection<U>> V transform(
            Collection<T> original, Transformer<T, U> transformer, V transformed) {
        for (T item : original) {
            try {
                transformed.add(transformer.transform(item));
            } catch (Exception e) {
                throw new RetrosheetRuntimeException("Unable to transform: " +
                        item, e);
            }
        }
        return transformed;
    }
    
    public static <T, U> List<U> transform(Collection<T> original,
            Transformer<T, U> transformer) {
        return transform(original, transformer, new ArrayList<U>(original.size()));
    }
    
    public static <T, U> Set<U> transform(Set<T> original,
            Transformer<T, U> transformer) {
        return transform(original, transformer, new HashSet<U>(original.size()));
    }
    
    @SuppressWarnings("unchecked")
    public static <T, U extends Collection<T>> U newCollectionFromInterface(
            Class<U> collectionClass) {
        if (BlockingDeque.class.isAssignableFrom(collectionClass)) {
            return (U) new LinkedBlockingDeque<T>();
        } else if (BlockingQueue.class.isAssignableFrom(collectionClass)) {
            return (U) new LinkedBlockingQueue<T>();
        } else if (Queue.class.isAssignableFrom(collectionClass)) {
            return (U) new ArrayDeque<T>();
        } else if (List.class.isAssignableFrom(collectionClass)) {
            return (U) new ArrayList<T>();
        } else if (SortedSet.class.isAssignableFrom(collectionClass)) {
            return (U) new TreeSet<T>();
        } else if (Set.class.isAssignableFrom(collectionClass)) {
            return (U) new HashSet<T>();
        } else if (Collection.class.isAssignableFrom(collectionClass)) {
            return (U) new ArrayList<T>();
        } else {
            throw new BaseRuntimeException("Unrecognized interface: " + collectionClass);
        }
    }
    
    private CollectionUtils() {
        
    }
}
