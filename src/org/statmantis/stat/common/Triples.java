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
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Triples",
        abbreviations = "3B",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class Triples extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int triples = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.TRIPLE) {
            triples++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return triples;
    }

}
