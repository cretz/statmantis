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

import org.statmantis.model.Play;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.StatisticUtils;

@StatisticInfo(
        name = "Sacrifice Flies",
        abbreviations = "SF",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class SacrificeFlies extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int sacrificeFlies = 0;

    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && StatisticUtils.isSacrificeFly(play)) {
            sacrificeFlies++;
        }
    }
    
    @Override
    public Integer getCurrentValue() {
        return sacrificeFlies;
    }
}
