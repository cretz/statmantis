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
 * Representation of a spot in a {@link Park}
 * 
 * @author Chad Retz
 */
public enum FieldLocation implements RetrosheetModel {
    MOUND("1", "Mound", Position.PITCHER),
    MOUND_FIRST("13", "Between mound and first"),
    MOUND_THIRD("15", "Between mound and third"),
    MOUND_FRONT("1S", "In front of mound"),
    HOME("2", "In front of home", Position.CATCHER),
    HOME_FIRST("23", "Between home and first"),
    HOME_FIRST_FOUL("23F", "Between home and first, foul"),
    HOME_THIRD("25", "In between home and third"),
    HOME_THIRD_FOUL("25F", "Between home and third, foul"),
    HOME_FOUL("2F", "Behind home, foul"),
    FIRST("3", "First base", Position.FIRST_BASE),
    FIRST_FOUL("3F", "First base, foul"),
    FIRST_FOUL_DEEP("3FD", "Behind first, foul"),
    FIRST_SHALLOW("3S", "In front of first"),
    FIRST_DEEP("3D", "Behind first"),
    FIRST_SECOND("34", "Between first and second"),
    FIRST_SECOND_SHALLOW("34S", "Between first and second, shallow"),
    FIRST_SECOND_DEEP("34D", "Between first and second, deep"),
    SECOND("4", "Second base", Position.SECOND_BASE),
    SECOND_SHALLOW("4S", "In front of second"),
    SECOND_DEEP("4D", "Behind second"),
    SECOND_MIDDLE("4M", "Second base, up the middle"),
    SECOND_MIDDLE_SHALLOW("4MS", "Second base, up the middle shallow"),
    SECOND_MIDDLE_DEEP("4MD", "Second base, up the middle deep"),
    THIRD("5", "Third base", Position.THIRD_BASE),
    THIRD_FOUL("5F", "Third base, foul"),
    THIRD_FOUL_DEEP("5FD", "Behind third, foul"),
    THIRD_SHALLOW("5S", "In front of third"),
    THIRD_DEEP("5D", "Behind third"),
    THIRD_SHORTSTOP("56", "Between third and shortstop"),
    THIRD_SHORTSTOP_SHALLOW("56S", "Between third and shortstop, shallow"),
    THIRD_SHORTSTOP_DEEP("56D", "Between third and shortstop, deep"),
    SHORTSTOP("6", "Shortstop", Position.SHORTSTOP),
    SHORTSTOP_SHALLOW("6S", "In front of shortstop"),
    SHORTSTOP_DEEP("6D", "Behind shortstop"),
    SHORTSTOP_MIDDLE("6M", "Shortstop, up the middle"),
    SHORTSTOP_MIDDLE_SHALLOW("6MS", "Shortstop, up the middle shallow"),
    SHORTSTOP_MIDDLE_DEEP("6MD", "Shortstop, up the middle deep"),
    LEFT("7", "Left field", Position.LEFT_FIELD),
    LEFT_FOUL("7LF", "Left field, foul"),
    LEFT_FOUL_SHALLOW("7SF", "Left field, foul shallow"),
    LEFT_FOUL_DEEP("7LDF", "Left field, foul deep"),
    LEFT_SHALLOW("7S", "In front of left"),
    LEFT_DEEP("7D", "Behind left"),
    LEFT_CENTER("78", "Left center"),
    LEFT_CENTER_SHALLOW("78S", "Left center, shallow"),
    LEFT_CENTER_DEEP("78D", "Left center, deep"),
    LEFT_CENTER_WALL("78XD", "Left center, really deep"),
    LEFT_LINE("7L", "Left line"),
    LEFT_LINE_SHALLOW("7LS", "Left line, shallow"),
    LEFT_LINE_DEEP("7LD", "Left line, deep"),
    CENTER("8", "Center field"),
    CENTER_SHALLOW("8S", "In front of center"),
    CENTER_DEEP("8D", "Behind center", Position.CENTER_FIELD),
    CENTER_WALL("8XD", "Behind center, deep"),
    RIGHT("9", "Right field", Position.RIGHT_FIELD),
    RIGHT_FOUL("9LF", "Right field, foul"),
    RIGHT_FOUL_SHALLOW("9SF", "Right field, foul shallow"),
    RIGHT_FOUL_DEEP("9LDF", "Right field, foul deep"),
    RIGHT_SHALLOW("9S", "In front of right"),
    RIGHT_DEEP("9D", "Behind right"),
    RIGHT_CENTER("89", "Right center"),
    RIGHT_CENTER_SHALLOW("89S", "Right center, shallow"),
    RIGHT_CENTER_DEEP("89D", "Right center, deep"),
    RIGHT_CENTER_WALL("89XD", "Right center, really deep"),
    RIGHT_LINE("8L", "Right line"),
    RIGHT_LINE_SHALLOW("8LS", "Right line, shallow"),
    RIGHT_LINE_DEEP("8LD", "Right line, deep");
    
    public static final int MAX_LENGTH = 24;
    public static final Map<String, FieldLocation> LOCATIONS;
    
    static {
        LOCATIONS = EnumUtils.mapEnumerates(FieldLocation.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    private final Position position;
    
    private FieldLocation(String retroId, String friendlyName) {
        this(retroId, friendlyName, null);
    }
    
    private FieldLocation(String retroId, String friendlyName, Position position) {
        this.retroId = retroId;
        this.friendlyName = friendlyName;
        this.position = position;
    }
    
    @Override
    public String getRetroId() {
        return retroId;
    }
    
    public String getFriendlyName() {
        return friendlyName;
    }
    
    public Position getPosition() {
        return position;
    }
}
