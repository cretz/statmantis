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

import java.util.Arrays;
import java.util.List;

import org.statmantis.model.Play;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

/**
 * Statistic that represents an At Bat (AB). The parameters which define
 * an at bat can be found <a href="http://en.wikipedia.org/wiki/At_bat">here</a>
 * on Wikipedia. This is considered a batting and pitching statistic. 
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "At Bats",
        abbreviations = "AB",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class AtBats extends PitcherAndBatterBasedPlayStatistic<Integer> {

    public static final List<PlayType> INVALID_PLAY_TYPES =
        Arrays.asList(
                PlayType.HIT_BY_PITCH,
                PlayType.INTENTIONAL_WALK,
                PlayType.WALK,
                PlayType.BALK,
                PlayType.INTERFERENCE,
                PlayType.ERROR_FOUL_FLY,
                PlayType.CAUGHT_STEALING,
                PlayType.DEFENSIVE_INDIFFERENCE,
                PlayType.RUNNER_ADVANCEMENT,
                PlayType.PASSED_BALL,
                PlayType.WILD_PITCH,
                PlayType.PICKOFF,
                PlayType.PICKOFF_CAUGHT_STEALING,
                PlayType.STOLEN_BASE);
    
    private int atBats = 0;
    
    protected boolean isQualifiedAtBat(Play play) {
        //me?
        if (isPlayApplicable(play)) {
            return false;
        }
        //must be a batter involved qualifying play
        if (INVALID_PLAY_TYPES.contains(play.getEvent().getPlayType())) {
            return false;
        }
        //no sacs
        if (play.getEvent().getModifiers() != null) {
            for (PlayModifier modifier : play.getEvent().getModifiers()) {
                if (modifier.getType() == PlayModifierType.SACRIFICE_FLY ||
                        modifier.getType() == PlayModifierType.SACRIFICE_HIT) {
                    return false;
                }
            }
        }
        //TODO: handle the issue where there was two strikes and 
        //  he was replaced and the other batter struck out
        return true;
    }

    @Override
    public void applyPlay(Play play) {
        if (isQualifiedAtBat(play)) {
            atBats++;
        }
    }
    
    @Override
    public Integer getCurrentValue() {
        return atBats;
    }

}
