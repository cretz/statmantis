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
 * Reference to a base.
 * <a href="http://www.retrosheet.org/eventfile.htm">Retrosheet reference</a>
 * 
 * @author Chad Retz
 */
public enum Base implements RetrosheetModel {
    FIRST("1", "First base"),
    SECOND("2", "Second base"),
    THIRD("3", "Third base"),
    HOME("H,B", "Home plate");

    public static final int MAX_LENGTH = 6;
    public static final Map<String, Base> BASES;
    
    static {
        BASES = EnumUtils.mapEnumerates(Base.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private Base(String retroId, String friendlyName) {
        this.retroId = retroId;
        this.friendlyName = friendlyName;
    }
    
    public String getRetroId() {
        return retroId;
    }
    
    public String getFriendlyName() {
        return friendlyName;
    }
    
    public Base nextBase() {
        if (this == HOME) {
            return FIRST;
        } else {
            return values()[ordinal() + 1];
        }
    }
    
    public Base previousBase() {
        if (this == FIRST) {
            return HOME;
        } else {
            return values()[ordinal() - 1];
        }
    }
}
