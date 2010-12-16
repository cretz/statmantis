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
 * Time for a {@link Transaction}
 * 
 * @author Chad Retz
 */
public enum TransactionTime implements RetrosheetModel {
    BEFORE_GAMES("", "Before games"),
    AFTER_FROM_GAME_BEFORE_TO_GAME("1", "After from team's first/only game " +
            "but before to-team's last/only game"),
    AFTER_GAMES("2", "After all games");
    
    public static final int MAX_LENGTH = 30;
    public static final Map<String, TransactionTime> TIMES;
    
    static {
        TIMES = EnumUtils.mapEnumerates(TransactionTime.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private TransactionTime(String retroId, String friendlyName) {
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
