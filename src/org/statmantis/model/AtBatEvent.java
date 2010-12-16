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
 * Representation of an event during an at bat.
 * <a href="http://www.retrosheet.org/eventfile.htm#3">Retrosheet reference</a>
 * 
 * @author Chad Retz
 */
public enum AtBatEvent implements RetrosheetModel {
    CATCHER_PICKOFF("+", "Pickoff throw by catcher"),
    CATCHER_BLOCKED_NEXT_PITCH("*", "Following pitch blocked by catcher"),
    BATTER_NOT_INVOLED_ON_PLAY(".", "Play that didn't involve batter"),
    PICKOFF_TO_FIRST("1", "Pickoff throw to first"),
    PICKOFF_TO_SECOND("2", "Pickoff throw to second"),
    PICKOFF_TO_THIRD("3", "Pickoff throw to third"),
    RUNNER_GOING_NEXT_PITCH(">", "Runner stealing on next pitch"),
    BALL("b", "Ball"),
    STRIKE_CALLED("c", "Called strike"),
    FOUL("f", "Foul"),
    HIT_BATTER("h", "Hit batter"),
    INTENTIONAL_BALL("i", "Intentional ball"),
    STRIKE("s", "Strike"),
    BUNT_FOUL("l", "Foul bunt"),
    BUNT_MISSED("m", "Missed bunt attempt"),
    NO_PITCH("n", "No pitch"),
    BUNT_FOUL_TIP("o", "Foul tip on bunt"),
    PITCHOUT("p", "Pitchout"),
    PITCHOUT_SWINGING("q", "Swinging on pitchout"),
    PITCHOUT_FOUL("r", "Foul ball on pitchout"),
    STRIKE_SWINGING("s", "Swinging strike"),
    FOUL_TIP("t", "Foul tip"),
    UNKNOWN_PITCH("u", "Unknown pitch"),
    BALL_PITCHER_MOUTH("v", "Ball because pitcher went to his mouth"),
    IN_PLAY("x", "Batter put ball in play"),
    IN_PLAY_PITCHOUT("y", "Button put pitchout in play");

    public static final Map<String, AtBatEvent> EVENTS;
    
    static {
        EVENTS = EnumUtils.mapEnumerates(AtBatEvent.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private AtBatEvent(String retroId, String friendlyName) {
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
