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
package org.statmantis.mport.retro;

import java.util.HashMap;
import java.util.Map;

import org.statmantis.model.League;
import org.statmantis.model.Level;
import org.statmantis.model.Park;
import org.statmantis.model.Person;
import org.statmantis.model.Team;

/**
 * Context for use during import
 * 
 * @author Chad Retz
 */
public class RetrosheetContext {
    
    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String TIME_FORMAT = "h:mma";
    
    private static League buildLeague(String abbreviation, Level level, 
            String name, String retroId) {
        League league = new League();
        league.setAbbreviation(abbreviation);
        league.setLevel(level);
        league.setName(name);
        league.setRetroId(retroId);
        return league;
    }

    private final Map<String, Team> teams = new HashMap<String, Team>();
    private final Map<String, League> leagues = new HashMap<String, League>();
    private final Map<String, Person> persons = new HashMap<String, Person>();
    private final Map<String, Park> parks = new HashMap<String, Park>();
    
    public RetrosheetContext() {
        leagues.put("T", buildLeague("NA", Level.ML, "National Association", "NA"));
        leagues.put("N", buildLeague("NL", Level.ML, "National League", "NL"));
        leagues.put("M", buildLeague("AA", Level.ML, "American Association", "AA"));
        leagues.put("U", buildLeague("UA", Level.ML, "Union Association", "UA"));
        leagues.put("P", buildLeague("PL", Level.ML, "Players League", "PL"));
        leagues.put("A", buildLeague("AL", Level.ML, "American League", "AL"));
        leagues.put("F", buildLeague("FL", Level.ML, "Federal League", "FL"));
    }

    /**
     * All teams indexed by retroId
     * 
     * @return
     */
    public Map<String, Team> getTeams() {
        return teams;
    }

    /**
     * All leagues indexed by retroId
     * 
     * @return
     */
    public Map<String, League> getLeagues() {
        return leagues;
    }

    /**
     * All persons indexed by retroId
     * 
     * @return
     */
    public Map<String, Person> getPersons() {
        return persons;
    }
    
    /**
     * All parks indexed by retroId
     * 
     * @return
     */
    public Map<String, Park> getParks() {
        return parks;
    }

}
