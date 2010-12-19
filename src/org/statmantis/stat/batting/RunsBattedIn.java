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
package org.statmantis.stat.batting;

import org.statmantis.model.Base;
import org.statmantis.model.Play;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayRunnerAdvancement;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.StatisticUtils;

/**
 * This calculates the Runs Batted In (RBI) for a batter. See the Wikipedia
 * reference <a href="http://en.wikipedia.org/wiki/Run_batted_in">here</a>. This
 * is a batting only statistic.
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Runs Batted In",
        abbreviations = "RBI",
        types = StatisticType.BATTING)
public class RunsBattedIn extends BatterBasedPlayStatistic<Integer> {

    private int runsBattedIn = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (!isPlayApplicable(play)) {
            return;
        }
        //TODO: I think this is all wrong
        //ok, let's go by the rule book
        int accountedForRuns = 0;
        int unaccountedForRuns = 0;
        //10.04(a)(1)
        //safe hit? sac? FC? infield out? bb w/ bases loaded
        Base baseFrom = null;
        if (play.getEvent().getPlayType() == PlayType.SINGLE ||
                StatisticUtils.isSacrifice(play) ||
                play.getEvent().getPlayType() == PlayType.GROUND_OUT ||
                play.getEvent().getPlayType() == PlayType.FIELDERS_CHOICE) {
            baseFrom = Base.THIRD;
        } else if (play.getEvent().getPlayType() == PlayType.DOUBLE ||
                play.getEvent().getPlayType() == PlayType.GROUND_RULE_DOUBLE) {
            baseFrom = Base.SECOND;
        } else if (play.getEvent().getPlayType() == PlayType.TRIPLE) {
            baseFrom = Base.FIRST;
        } else if (play.getEvent().getPlayType() == PlayType.HOME_RUN) {
            baseFrom = Base.HOME;
            accountedForRuns++;
        } else if (StatisticUtils.isLoadedBases(play) && (
                play.getEvent().getPlayType() == PlayType.WALK ||
                play.getEvent().getPlayType() == PlayType.INTENTIONAL_WALK ||
                play.getEvent().getPlayType() == PlayType.HIT_BY_PITCH ||
                play.getEvent().getPlayType() == PlayType.INTERFERENCE ||
                play.getEvent().getPlayType() == PlayType.WALK)) {
            baseFrom = Base.THIRD;
        }
        for (PlayRunnerAdvancement advancement : play.getAdvancements()) {
            if (advancement.getBaseTo() == Base.HOME) {
                if (advancement.getBaseFrom().ordinal() >= baseFrom.ordinal()) {
                    accountedForRuns++;
                } else {
                    unaccountedForRuns++;
                }
            }
        }
        if (accountedForRuns > 0 || unaccountedForRuns > 0) {
            //any RBI's expected that shouldn't be or vice-versa?
            for (PlayModifier modifier : play.getEvent().getModifiers()) {
                if (modifier.getType() == PlayModifierType.RBI && unaccountedForRuns > 0) {
                    unaccountedForRuns--;
                    accountedForRuns++;
                } else if (modifier.getType() == PlayModifierType.NO_RBI && accountedForRuns > 0) {
                    accountedForRuns--;
                    unaccountedForRuns++;
                }
            }
            runsBattedIn += accountedForRuns;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return runsBattedIn;
    }

}
