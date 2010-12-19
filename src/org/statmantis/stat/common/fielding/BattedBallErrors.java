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
package org.statmantis.stat.common.fielding;

import java.util.Set;

import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayType;
import org.statmantis.model.Team;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.FielderBasedStatistic;
import org.statmantis.stat.PlayBasedStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.StatisticUtils;
import org.statmantis.stat.TeamBasedStatistic;

import com.google.common.collect.Sets;

@StatisticInfo(
        name = "Batted Ball Errors",
        types = StatisticType.FIELDING)
public class BattedBallErrors extends AbstractStatistic<Integer> 
        implements PlayBasedStatistic, FielderBasedStatistic, TeamBasedStatistic {

    private static final Set<PlayType> INVALID_PLAY_TYPES = Sets.newHashSet(
                PlayType.INTERFERENCE,
                PlayType.HIT_BY_PITCH,
                PlayType.STRIKE_OUT,
                PlayType.INTENTIONAL_WALK,
                PlayType.WALK,
                PlayType.BALK,
                PlayType.CAUGHT_STEALING,
                PlayType.DEFENSIVE_INDIFFERENCE,
                PlayType.RUNNER_ADVANCEMENT,
                PlayType.PASSED_BALL,
                PlayType.WILD_PITCH,
                PlayType.PICKOFF,
                PlayType.PICKOFF_CAUGHT_STEALING,
                PlayType.STOLEN_BASE);
    
    private int errors;
    private Team team;
    private Person fielder;
    
    @Override
    public void setCurrentTeam(Team team) {
        this.team = team;
    }

    @Override
    public void setCurrentFielder(Person fielder) {
        this.fielder = fielder;
    }

    @Override
    public void applyPlay(Play play) {
        if (team != null && !StatisticUtils.isTeamOnField(team, play)) {
            return;
        }
        if (INVALID_PLAY_TYPES.contains(play.getEvent().getPlayType())) {
            return;
        }
        //applicable play type?
        for (PlayModifier modifier : play.getEvent().getModifiers()) {
            if (modifier.getType() == PlayModifierType.ERROR) {
                if (team != null) {
                    errors++;
                } else if (fielder != null && fielder.equals(modifier.
                        getFielder().getPlayer())) {
                    errors++;
                }
            }
        }
    }
    @Override
    public Integer getCurrentValue() {
        return errors;
    }


}
