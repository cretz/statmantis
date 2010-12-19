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
 * Interface {@link Statistic}s must implement in order to
 * be considered {@link StatisticType#RUNNING} statistics.
 *
 * @author Chad Retz
 */
public interface RunnerBasedStatistic {

    /**
     * Set the runner that this statistic applies to. This
     * will only be called once per statistic instantiation.
     * 
     * @param runner
     */
    void setCurrentRunner(Person runner);
}
