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
package org.statmantis.stat.common;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "At Bats",
        abbreviations = "AB",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Singles.class,
                Doubles.class,
                Triples.class,
                HomeRuns.class
        })
public class TotalBases extends AbstractStatistic<Integer> {

    @Override
    public Integer getCurrentValue() {
        int ret = getDependency(Singles.class);
        ret += getDependency(Doubles.class) * 2;
        ret += getDependency(Triples.class) * 3;
        ret += getDependency(HomeRuns.class) * 4;
        return ret;
    }

}
