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
 * Number of the game for the day of a {@link GameDayNumber}
 * 
 * @author Chad Retz
 */
public enum GameDayNumber implements RetrosheetModel {

    SINGLE_GAME("0", "Single game"),
    FIRST_GAME("1", "First game of double/triple header"),
    SECOND_GAME("2", "Second game of double/triple header"),
    THIRD_GAME("3", "Third game of triple header"),
    FIRST_GAME_THREE_WAY("A", "First game of three team double header"),
    SECOND_GAME_THREE_WAY("B", "Second game of three team double header");
    
    public static final int MAX_LENGTH = 21;
    public static final Map<String, GameDayNumber> NUMBERS;
    
    static {
        NUMBERS = EnumUtils.mapEnumerates(GameDayNumber.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private GameDayNumber(String retroId, String friendlyName) {
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
