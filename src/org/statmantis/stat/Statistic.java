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
package org.statmantis.stat;

import com.google.common.collect.ClassToInstanceMap;

/**
 * Base interface for all statistics. All statistics must have
 * a {@link StatisticInfo} annotation defining them.
 *
 * @author Chad Retz
 */
public interface Statistic<T extends Number> {

    /**
     * Set the dependencies for this statistic. This will set all dependencies
     * that defined by {@link StatisticInfo#dependencies()}
     *  
     * @param dependencies
     */
    void setCurrentDependencies(ClassToInstanceMap<Statistic<? extends Number>> dependencies);
    
    /**
     * Get the current value of this statistic
     * 
     * @return
     */
    T getCurrentValue();
}
