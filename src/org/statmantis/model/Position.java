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

/**
 * Representation of a position for a {@link GamePlayer}
 * 
 * @author Chad Retz
 */
public enum Position implements RetrosheetModel {
    
    PITCHER(FieldLocation.MOUND),
    CATCHER(FieldLocation.HOME),
    FIRST_BASE(FieldLocation.FIRST),
    SECOND_BASE(FieldLocation.SECOND),
    THIRD_BASE(FieldLocation.THIRD),
    SHORTSTOP(FieldLocation.SHORTSTOP),
    LEFT_FIELD(FieldLocation.LEFT),
    CENTER_FIELD(FieldLocation.CENTER_DEEP),
    RIGHT_FIELD(FieldLocation.RIGHT),
    DESIGNATED_HITTER(null),
    PINCH_HITTER(null),
    PINCH_RUNNER(null);
    
    public static final int MAX_LENGTH = 17;
    
    public static Position fromRetroId(int id) {
        return Position.values()[id - 1];
    }
    
    private final FieldLocation location;
    
    private Position(FieldLocation location) {
        this.location = location;
    }

    @Override
    public String getRetroId() {
        return String.valueOf(ordinal() + 1);
    }
    
    public FieldLocation getLocation() {
        return location;
    }

}
