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
package org.statmantis.model;

import java.util.Map;

import org.statmantis.util.EnumUtils;

/**
 * Representation of the sky condition
 * for a {@link Game}
 * 
 * @author Chad Retz
 */
public enum SkyCondition implements RetrosheetModel {
    CLOUDY("cloudy", "Cloudy"),
    DOME("dome", "Dome"),
    NIGHT("night", "Night"),
    OVERCAST("overcast", "Overcast"),
    SUNNY("sunny", "Sunny");
    
    public static final int MAX_LENGTH = 8;
    public static final Map<String, SkyCondition> CONDITIONS;
    
    static {
        CONDITIONS = EnumUtils.mapEnumerates(SkyCondition.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private SkyCondition(String retroId, String friendlyName) {
        this.retroId = retroId;
        this.friendlyName = friendlyName;
    }
    
    @Override
    public String getRetroId() {
        return retroId;
    }
    
    public String getFriendlyName() {
        return friendlyName;
    }
}
