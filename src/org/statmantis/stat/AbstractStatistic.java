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

import java.math.BigDecimal;

import com.google.common.collect.ClassToInstanceMap;

/**
 * Base class for {@link Statistic}s that hold references to the dependencies
 *
 * @author Chad Retz
 */
public abstract class AbstractStatistic<T extends Number> implements Statistic<T> {
    
    protected static final BigDecimal NINE = new BigDecimal(9); 

    private ClassToInstanceMap<Statistic<? extends Number>> dependencies;
    
    @Override
    public final void setCurrentDependencies(
            ClassToInstanceMap<Statistic<? extends Number>> dependencies) {
        this.dependencies = dependencies;
    }
    
    /**
     * Get a dependency that was defined in {@link StatisticInfo#dependencies()}
     * 
     * @param <U>
     * @param statistic
     * @return
     */
    protected <U extends Number> U getDependency(Class<? extends Statistic<U>> statistic) {
        Statistic<U> stat = dependencies.getInstance(statistic);
        if (stat == null) {
            throw new IllegalArgumentException("Statistic " + statistic + " not found");
        }
        return stat.getCurrentValue();
    }
}
