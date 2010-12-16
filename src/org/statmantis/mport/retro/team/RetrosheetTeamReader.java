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
package org.statmantis.mport.retro.team;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections15.Transformer;
import org.statmantis.model.Team;
import org.statmantis.mport.retro.RetrosheetContext;
import org.statmantis.mport.retro.RetrosheetFlatCsvEnumReader;
import org.statmantis.util.MapUtils;

/**
 * Team file reader
 *
 * @author Chad Retz
 */
public class RetrosheetTeamReader extends RetrosheetFlatCsvEnumReader<Team, TeamColumn> {

    private static final String URL = "http://www.retrosheet.org/TEAMABR.TXT";
    
    private final RetrosheetContext retroContext;
    
    public RetrosheetTeamReader(RetrosheetContext retroContext) {
        this.retroContext = retroContext;
    }
    
    @Override
    protected String getUrl() {
        return URL;
    }
    
    @Override
    protected Class<TeamColumn> getEnumClass() {
        return TeamColumn.class;
    }
    
    @Override
    protected boolean isFirstLineSkipped() {
        return true;
    }
    
    @Override
    protected Transformer<Map<TeamColumn, String>, Entry<String, Team>> getTransformer() {
        return new Transformer<Map<TeamColumn, String>, Entry<String, Team>>() {
            @Override
            public Entry<String, Team> transform(final Map<TeamColumn, String> csv) {
                Team model = new Team();
                model.setRetroId(csv.get(TeamColumn.TEAM_ID));
                model.setLeague(retroContext.getLeagues().get(csv.get(TeamColumn.LEAGUE)));
                model.setCity(csv.get(TeamColumn.CITY));
                model.setName(csv.get(TeamColumn.NAME));
                return MapUtils.createEntry(model.getRetroId(), model);
            }
        };
    }    
}
