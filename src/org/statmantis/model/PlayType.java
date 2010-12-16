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
 * Representation of a type for a {@link Play}
 * 
 * @author Chad Retz
 */
public enum PlayType implements RetrosheetModel {
    FLY_OUT("[1-9]", "Fly ball out"),
    GROUND_OUT("[1-9]*(\\([1-3BH]\\))?", "Ground ball out"),
    GROUND_OUT_DOUBLE_PLAY("[1-9][1-9]?\\([1-3BH]\\)[1-9]", "Grounded into double play"),
    LINE_OUT_DOUBLE_PLAY("[1-9]\\(B\\)[1-9][1-9]?\\([1-3BH]\\)", "Lined into double play"),
    GROUND_OUT_TRIPLE_PLAY("[1-9][1-9]?\\([1-3BH]\\)[1-9][1-9]?\\([1-3BH]\\)[1-9]", "Grounded into triple play"),
    LINE_OUT_TRIPLE_PLAY("[1-9]\\(B\\)[1-9][1-9]?\\([1-3BH]\\)[1-9][1-9]?\\([1-3BH]\\)", "Lined into triple play"),
    INTERFERENCE("C", "Interference"),
    SINGLE("S[1-9]?", "Single"),
    DOUBLE("D[1-9]?", "Double"),
    TRIPLE("T[1-9]?", "Triple"),
    GROUND_RULE_DOUBLE("DGR", "Ground rule double"),
    ERROR("E[1-9]", "Error"),
    FIELDERS_CHOICE("FC[1-9]", "Fielder's choice"),
    ERROR_FOUL_FLY("FLE[1-9]", "Error on foul fly ball"),
    HOME_RUN("HR?[0-9]?", "Home run"),
    HIT_BY_PITCH("HBP", "Batter hit by pitch"),
    STRIKE_OUT("K", "Strike out"),
    INTENTIONAL_WALK("IW?", "Intentional walk"),
    WALK("W", "Walk"),
    BALK("BK", "Balk"),
    CAUGHT_STEALING("CS[1-3BH]\\([1-9]E?[1-9]\\)", "Caught stealing"),
    DEFENSIVE_INDIFFERENCE("DI", "Defensive indifference"),
    RUNNER_ADVANCEMENT("OA", "Runner advancement"),
    PASSED_BALL("PB", "Passed ball"),
    WILD_PITCH("WP", "Wild pitch"),
    PICKOFF("PO[1-3]\\([1-9E]?[1-9]\\)", "Pickoff"),
    PICKOFF_CAUGHT_STEALING("POCS[1-3]\\([1-9E][1-9E]*\\)", "Pickoff caught stealing"),
    STOLEN_BASE("SB[23H]", "Stolen base");
    
    public static final int MAX_LENGTH = 23;
    public static final Map<String, PlayType> TYPES;
    
    static {
        TYPES = EnumUtils.mapEnumerates(PlayType.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private PlayType(String retroId, String friendlyName) {
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
