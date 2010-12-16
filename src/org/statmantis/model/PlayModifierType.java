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
 * Representation of a modifier that can occur in
 * a {@link PlayEvent}
 * 
 * @author Chad Retz
 *
 */
public enum PlayModifierType implements RetrosheetModel {
    BUNT("B", "Bunt"),
    FLY_BALL_BUNT("BF", "Fly ball bunt"),
    GROUND_BALL_BUNT("BG", "Ground ball bunt"),
    BUNT_GROUND_OUT_DOUBLE_PLAY("BGDP", "Bunt grounded into double play"),
    BUNT_LINE_DRIVE("BL", "Line drive bunt"),
    BUNT_POP_UP("BP", "Bunt pop up"),
    BUNT_POP_UP_DOUBLE_PLAY("BPDP", "Bunt popped into double play"),
    RUNNER_HIT_BY_BALL("BR", "Runner hit by batted ball"),
    CALLED_THIRD_STRIKE("C", "Called third strike"),
    DOUBLE_PLAY("DP", "Double play"),
    ERROR("E", "Error"),
    FLY("F", "Fly"),
    FLY_DOUBLE_PLAY("FDP", "Fly ball double play"),
    FOUL("FL", "Foul"),
    FORCE_OUT("FO", "Force out"),
    GROUND_BALL("G", "Ground ball"),
    GROUND_OUT_DOUBLE_PLAY("GDP", "Ground ball double play"),
    GROUND_OUT_TRIPLE_PLAY("GTP", "Ground ball triple play"),
    INTERFERENCE("INT", "Interference"),
    LINE_DRIVE("L", "Line drive"),
    LINE_DRIVE_DOUBLE_PLAY("LDP", "Lined into double play"),
    LINE_DRIVE_TRIPLE_PLAY("LTP", "Lined into triple play"),
    POP_FLY("P", "Pop fly"),
    RELAY_THROW("R", "Relay throw"),
    SACRIFICE_FLY("SF", "Sacrifice fly"),
    SACRIFICE_HIT("SH", "Sacrifice hit"),
    THROW("TH", "Throw"),
    TRIPLE_PLAY("TP", "Triple play"),
    UNEARNED_RUN("UR", "Unearned run"),
    RBI("RBI", "Run batted in"),
    NO_RBI("NR,NORBI", "No run batted in"),
    TEAM_UNEARNED_RUN("TUR", "Team unearned run");
    
    public static final int MAX_LENGTH = 27;
    public static final Map<String, PlayModifierType> MODIFIERS;
    
    static {
        MODIFIERS = EnumUtils.mapEnumerates(PlayModifierType.class);
    }
    
    private final String retroId;
    private final String[] possibleNames;
    private final String friendlyName;
    
    private PlayModifierType(String retroId, String friendlyName) {
        this.retroId = retroId;
        possibleNames = retroId.split(",");
        this.friendlyName = friendlyName;
    }
    
    @Override
    public String getRetroId() {
        return retroId;
    }
    
    public String[] getPossibleNames() {
        return possibleNames;
    }
    
    public String getFriendlyName() {
        return friendlyName;
    }
}
