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

import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.CatcherBasedStatistic;
import org.statmantis.stat.PitcherBasedStatistic;
import org.statmantis.stat.PlayBasedStatistic;
import org.statmantis.stat.RunnerBasedStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.StatisticUtils;

/**
 * This statistic calculates the number of times a player was Caught Stealing (CS).
 * See the Wikipedia reference <a href="http://en.wikipedia.org/wiki/Caught_stealing">here</a>.
 * This is a batting, pitching, running, and fielding (catcher) statistic.
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Caught Stealing",
        abbreviations = "CS",
        types = { StatisticType.BATTING, StatisticType.PITCHING, 
                  StatisticType.FIELDING, StatisticType.RUNNING })
public class CaughtStealing extends AbstractStatistic<Integer> implements PlayBasedStatistic, 
        PitcherBasedStatistic, RunnerBasedStatistic, CatcherBasedStatistic {

    private int caughtStealing = 0;
    private Person runner;
    private Person pitcher;
    private Person catcher;
    
    @Override
    public void setCurrentRunner(Person runner) {
        this.runner = runner;
    }

    @Override
    public void setCurrentPitcher(Person pitcher) {
        this.pitcher = pitcher;
    }
    
    @Override
    public void setCurrentCatcher(Person catcher) {
        this.catcher = catcher;
    }

    @Override
    public void applyPlay(Play play) {
        //is caught stealing?
        if (play.getEvent().getPlayType() != PlayType.CAUGHT_STEALING &&
                play.getEvent().getPlayType() != PlayType.PICKOFF_CAUGHT_STEALING) {
            return;
        }
        //is runner?
        if (runner != null && !runner.equals(play.getPlayer().getPlayer())) {
            return;
        }
        //is pitcher?
        if (pitcher != null && !pitcher.equals(play.getPitcher().getPlayer())) {
            return;
        }
        //is catcher?
        if (catcher != null && !StatisticUtils.isCatcherInPlay(catcher, play)) {
            return;
        }
        caughtStealing++;
    }
    
    @Override
    public Integer getCurrentValue() {
        return caughtStealing;
    }

}
