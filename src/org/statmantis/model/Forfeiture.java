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
 * Representation of a reason for forfeit in a {@link Game}
 * 
 * @author Chad Retz
 */
public enum Forfeiture implements RetrosheetModel {
    VISITOR("V", "Visitor forfeit"),
    HOME("H", "Home forfeit"),
    NO_DECISION("T", "No decision forfeit");
    
    public static final int MAX_LENGTH = 11;
    public static final Map<String, Forfeiture> FORFEITURES;
    
    static {
        FORFEITURES = EnumUtils.mapEnumerates(Forfeiture.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private Forfeiture(String retroId, String friendlyName) {
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
