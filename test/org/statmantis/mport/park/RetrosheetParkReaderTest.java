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
package org.statmantis.mport.park;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.statmantis.model.Park;
import org.statmantis.mport.retro.park.RetrosheetParkReader;

/**
 * Test for reading park files
 *
 * @author Chad Retz
 */
public class RetrosheetParkReaderTest {

    public static Map<String, Park> getParks() throws Exception {
        //XXX: there is a bug w/ a trailing '"' at the end of the
        //  line for "NYC12,Washington Park III"...it's removed
        //  in my test version
        //cached in the package here
        return new RetrosheetParkReader() {
            @Override
            public Map<String, Park> read() throws IOException {
                return read(RetrosheetParkReaderTest.class.getResourceAsStream(
                        "parkcode.txt"));
            }
        }.read();
    }
    
    @Test
    public void testRead() throws Exception {
        Assert.assertEquals(245, getParks().size());
    }
}
