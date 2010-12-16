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
package org.statmantis.mport.retro.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.map.Flat3Map;
import org.statmantis.model.Base;
import org.statmantis.model.Game;
import org.statmantis.model.GamePlayer;
import org.statmantis.model.Play;
import org.statmantis.model.TeamGameInfo;
import org.statmantis.mport.retro.RetrosheetContext;

/**
 * Context for what's happening in a game
 * 
 * @author Chad Retz
 */
class GameContext {

    private final RetrosheetContext retroContext;
    private final EventInformation info;
    private Game currentGame;
    private final Map<String, GamePlayer> currentPlayers =
        new HashMap<String, GamePlayer>(60);
    private int currentInning = 0;
    private final Map<Base, GamePlayer> onBase = new Flat3Map<Base, GamePlayer>();
    private boolean homeTeamBatting;
    
    GameContext(RetrosheetContext retroContext, EventInformation info) {
        this.retroContext = retroContext;
        this.info = info;
    }
    
    public RetrosheetContext getRetroContext() {
        return retroContext;
    }
    
    public EventInformation getInfo() {
        return info;
    }
    
    public Game getCurrentGame() {
        return currentGame;
    }
    
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
    
    public Map<String, GamePlayer> getCurrentPlayers() {
        return currentPlayers;
    }
    
    public int getCurrentInning() {
        return currentInning;
    }
    
    public void incrementCurrentInning() {
        currentInning++;
    }
    
    public Map<Base, GamePlayer> getOnBase() {
        return onBase;
    }
    
    public boolean isHomeTeamBatting() {
        return homeTeamBatting;
    }
    
    public void setHomeTeamBatting(boolean homeTeamBatting) {
        this.homeTeamBatting = homeTeamBatting;
    }
    
    public void newGame(String retroId) {
        //beginning of game
        if (currentGame != null) {
            //persist previous
            List<Game> games = info.getGames().get(currentGame.getStartDate());
            if (games == null) {
                games = new ArrayList<Game>();
                info.getGames().put(currentGame.getStartDate(), games);
            }
            games.add(currentGame);
        }
        //re-init game vars
        currentInning = 1;
        currentPlayers.clear();
        onBase.clear();
        currentGame = new Game();
        currentGame.setPlays(new ArrayList<Play>());
        currentGame.setHomeInfo(new TeamGameInfo());
        currentGame.getHomeInfo().setGamePlayers(new ArrayList<GamePlayer>(30));
        currentGame.setVisitorInfo(new TeamGameInfo());
        currentGame.getVisitorInfo().setGamePlayers(new ArrayList<GamePlayer>(30));
        currentGame.setRetroId(retroId);
    }
}
