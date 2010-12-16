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
 * Representation of wind direction for a {@link Game}
 * 
 * @author Chad Retz
 */
public enum WindDirection implements RetrosheetModel {
    FROM_LEFT_FIELD("fromlf", "From left field"),
    FROM_CENTER_FIELD("fromcf", "From center field"),
    FROM_RIGHT_FIELD("fromrf", "From right field"),
    LEFT_TO_RIGHT("ltor", "Left to right"),
    RIGHT_TO_LEFT("rtol", "Right to left"),
    TO_LEFT_FIELD("tolf", "To left field"),
    TO_CENTER_FIELD("tocf", "To center field"),
    TO_RIGHT_FIELD("torf", "To right field");
    
    public static final int MAX_LENGTH = 6;
    public static final Map<String, WindDirection> DIRECTIONS;
    
    static {
        DIRECTIONS = EnumUtils.mapEnumerates(WindDirection.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private WindDirection(String retroId, String friendlyName) {
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
