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
package org.statmantis.mport.game;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.statmantis.RetrosheetContextAwareTestBase;
import org.statmantis.model.Game;
import org.statmantis.mport.retro.game.RetrosheetGameLogReader;

/**
 * Tests for reading game logs
 *
 * @author Chad Retz
 */
public class RetrosheetGameLogReaderTest extends RetrosheetContextAwareTestBase {

    @Test
    public void testRead() throws Exception {
        //let's test w/ the 1970-1979 game logs...
        System.out.println(new RetrosheetGameLogReader(1970, 1979, retroContext) {
            public Map<String, Game> read() throws IOException {
                return read(RetrosheetGameLogReaderTest.class.getResourceAsStream("gl1970_79.zip"));
            }
        }.read());
    }
}
