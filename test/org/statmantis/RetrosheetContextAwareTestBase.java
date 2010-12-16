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
package org.statmantis;

import org.junit.BeforeClass;
import org.statmantis.mport.park.RetrosheetParkReaderTest;
import org.statmantis.mport.person.RetrosheetPersonReaderTest;
import org.statmantis.mport.retro.RetrosheetContext;
import org.statmantis.mport.team.RetrosheetTeamReaderTest;

/**
 * Base test class for tests that need a retrosheet context
 *
 * @author Chad Retz
 */
public abstract class RetrosheetContextAwareTestBase {

    protected static RetrosheetContext retroContext;
    
    @BeforeClass
    public static void oneTimeSetUp() throws Exception {
        //build the context
        retroContext = new RetrosheetContext();
        retroContext.getParks().putAll(RetrosheetParkReaderTest.getParks());
        retroContext.getPersons().putAll(RetrosheetPersonReaderTest.getPersons());
        retroContext.getTeams().putAll(RetrosheetTeamReaderTest.getTeams(retroContext));
    }
}
