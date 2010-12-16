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

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.statmantis.model.Game;
import org.statmantis.model.Roster;

/**
 * Information from an event ZIP file
 * 
 * @author Chad Retz
 */
public class EventInformation {
    private final Map<String, Roster> rosters = new HashMap<String, Roster>(40);
    private final Map<Date, List<Game>> games = new HashMap<Date, List<Game>>(200);
    
    EventInformation() {
    }
    
    public Map<String, Roster> getRosters() {
        return rosters;
    }
    
    public Map<Date, List<Game>> getGames() {
        return games;
    }
    
    public void merge(EventInformation info) {
        if (info == null) {
            return;
        }
        rosters.putAll(info.rosters);
        for (Entry<Date, List<Game>> game : info.games.entrySet()) {
            List<Game> presentGames = games.get(game.getKey());
            if (presentGames == null) {
                games.put(game.getKey(), game.getValue());
            } else {
                presentGames.addAll(game.getValue());
            }
        }
    }

}
