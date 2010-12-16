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
package org.statmantis.mport.retro.park;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections15.Transformer;
import org.statmantis.model.Park;
import org.statmantis.model.PinPointLocation;
import org.statmantis.model.StateProvince;
import org.statmantis.mport.retro.RetrosheetFlatCsvEnumReader;
import org.statmantis.util.MapUtils;

/**
 * Reader for reading parks
 * 
 * @author Chad Retz
 */
public class RetrosheetParkReader extends RetrosheetFlatCsvEnumReader<Park, ParkColumn> {

    private static final String URL = "http://www.retrosheet.org/parkcode.txt";
    
    @Override
    protected String getUrl() {
        return URL;
    }
    
    @Override
    protected Class<ParkColumn> getEnumClass() {
        return ParkColumn.class;
    }
    
    @Override
    protected boolean isFirstLineSkipped() {
        return true;
    }
    
    @Override
    protected Transformer<Map<ParkColumn, String>, Entry<String, Park>> getTransformer() {
        return new Transformer<Map<ParkColumn, String>, Entry<String, Park>>() {
            @Override
            public Entry<String, Park> transform(final Map<ParkColumn, String> csv) {
                Park model = new Park();
                model.setComments(csv.get(ParkColumn.NOTES));
                if (!"NA".equals(csv.get(ParkColumn.END))) {
                    model.setConclusion(formatSqlDateOrNull(csv.get(ParkColumn.END)));
                }
                if (!"NA".equals(csv.get(ParkColumn.START))) {
                    model.setInception(formatSqlDateOrNull(csv.get(ParkColumn.START)));
                }
                model.setLocation(new PinPointLocation(null, csv.get(ParkColumn.CITY),
                        StateProvince.STATES.get(csv.get(ParkColumn.STATE)), 
                        null, null));
                model.setName(csv.get(ParkColumn.NAME));
                model.setPreviousNames(splitAndTrimOrNull(
                        csv.get(ParkColumn.AKA)));
                model.setRetroId(csv.get(ParkColumn.PARK_ID));
                return MapUtils.createEntry(model.getRetroId(), model);
            }
        };
    }    
}
