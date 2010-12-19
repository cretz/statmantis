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

import org.statmantis.model.Game;
import org.statmantis.model.GamePlayer;
import org.statmantis.model.Person;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.BatterBasedStatistic;
import org.statmantis.stat.FielderBasedStatistic;
import org.statmantis.stat.GameBasedStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Games",
        abbreviations = "G",
        types = { StatisticType.BATTING, StatisticType.FIELDING })
public class Games extends AbstractStatistic<Integer>
        implements GameBasedStatistic, BatterBasedStatistic, FielderBasedStatistic {

    private int games = 0;
    private Person fielder;
    private Person batter;
    
    @Override
    public void setCurrentFielder(Person fielder) {
        this.fielder = fielder;
    }

    @Override
    public void setCurrentBatter(Person batter) {
        this.batter = batter;
    }

    @Override
    public void applyGame(Game game) {
        //XXX: Should this matter whether they hit and/or field or not?
        //  What about pitching a game?
        GamePlayer player = null;
        for (GamePlayer homePlayer : game.getHomeInfo().getGamePlayers()) {
            if ((fielder != null && fielder.equals(homePlayer.getPlayer())) ||
                    (batter != null && batter.equals(homePlayer.getPlayer()))) {
                player = homePlayer;
                break;
            }
        }
        if (player == null) {
            for (GamePlayer visitingPlayer : game.getVisitorInfo().getGamePlayers()) {
                if ((fielder != null && fielder.equals(visitingPlayer.getPlayer())) ||
                        (batter != null && batter.equals(visitingPlayer.getPlayer()))) {
                    player = visitingPlayer;
                    break;
                }
            }
        }
        if (player != null && !player.getPositions().isEmpty()) {
            games++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return games;
    }
}
