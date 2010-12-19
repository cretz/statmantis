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
package org.statmantis.stat.pitching;

import java.util.ArrayList;
import java.util.List;

import org.statmantis.model.Game;
import org.statmantis.model.GamePlayer;
import org.statmantis.model.Person;
import org.statmantis.model.Team;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.GameBasedStatistic;
import org.statmantis.stat.PitcherBasedStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.TeamBasedStatistic;

@StatisticInfo(
        name = "Earned Runs",
        abbreviations = "ER",
        types = StatisticType.PITCHING)
public class EarnedRuns extends AbstractStatistic<Integer>
        implements GameBasedStatistic, PitcherBasedStatistic, TeamBasedStatistic {

    private int earnedRuns = 0;
    private Team team;
    private Person pitcher;
    
    @Override
    public void setCurrentTeam(Team team) {
        this.team = team;
    }
    
    @Override
    public void setCurrentPitcher(Person pitcher) {
        this.pitcher = pitcher;
    }
    
    @Override
    public void applyGame(Game game) {
        List<GamePlayer> applicablePlayers = new ArrayList<GamePlayer>();
        //certain team?
        if (team == null || team.equals(game.getHomeTeam())) {
            applicablePlayers.addAll(game.getHomeInfo().getGamePlayers());
        }
        if (team == null || team.equals(game.getVisitingTeam())) {
            applicablePlayers.addAll(game.getVisitorInfo().getGamePlayers());
        }
        for (GamePlayer player : applicablePlayers) {
            if (player.getEarnedRuns() != null && 
                    (pitcher == null || pitcher.equals(player.getPlayer()))) {
                earnedRuns += player.getEarnedRuns();
            }
        }
    }

    @Override
    public Integer getCurrentValue() {
        return earnedRuns;
    }

}
