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

/**
 * This is the number of Doubles (2B) hit by a player. See the Wikipedia
 * reference <a href="http://en.wikipedia.org/wiki/Double_%28baseball%29">here</a>.
 * This is a batting and pitching statistic. 
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Doubles",
        abbreviations = "2B",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class Doubles extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int doubles = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && (play.getEvent().
                getPlayType() == PlayType.DOUBLE || play.getEvent().
                getPlayType() == PlayType.GROUND_RULE_DOUBLE)) {
            doubles++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return doubles;
    }

}
