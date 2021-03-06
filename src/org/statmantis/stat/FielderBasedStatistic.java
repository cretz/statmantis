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

import org.statmantis.model.Person;

/**
 * Interface all {@link Statistic}s must implement in order
 * to have {@link StatisticType#FIELDING} statistics.
 *
 * @author Chad Retz
 */
public interface FielderBasedStatistic {

    /**
     * Set the current fielder for this statistic. This is only
     * called once per statistic instantiation.
     * 
     * @param fielder
     */
    void setCurrentFielder(Person fielder);
}
