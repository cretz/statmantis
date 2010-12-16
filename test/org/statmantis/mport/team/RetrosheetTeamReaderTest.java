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
package org.statmantis.mport.team;

import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.statmantis.model.Team;
import org.statmantis.mport.retro.RetrosheetContext;
import org.statmantis.mport.retro.team.RetrosheetTeamReader;

/**
 * Tests for the team reader
 *
 * @author Chad Retz
 */
public class RetrosheetTeamReaderTest {

    public static Map<String, Team> getTeams(RetrosheetContext retroContext) throws Exception {
        return new RetrosheetTeamReader(retroContext) {
            public Map<String, Team> read() throws IOException {
                return read(RetrosheetTeamReaderTest.class.getResourceAsStream("TEAMABR.TXT"));
            }
        }.read();
    }
    
    @Test
    public void testRead() throws Exception {
        Assert.assertEquals("Size didn't match", 148,
                getTeams(new RetrosheetContext()).size());
    }

}
