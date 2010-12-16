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
package org.statmantis.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.statmantis.model.Game;
import org.statmantis.model.GameDayNumber;

/**
 * Utilities for a {@link Game}
 *
 * @author Chad Retz
 */
public class GameUtils {

    public static String generateUniqueId(Game game) {
        return generateUniqueId(game.getStartDate(), 
                game.getHomeTeam().getRetroId(), 
                game.getVisitingTeam().getRetroId(), 
                game.getDayNumber());
    }
    
    public static String generateUniqueId(Date startDate, 
            String homeTeamRetroId, String visitingTeamRetroId,
            GameDayNumber gameDayNumber) {
        return startDate.getTime() + "-" + homeTeamRetroId + "-" +
                visitingTeamRetroId + "-" + gameDayNumber.getRetroId();
    }
    
    public static List<Integer> parseLineScore(String lineScore) {
        //yay, arraylist defaults at 10, good enough for performance here
        List<Integer> ret = new ArrayList<Integer>();
        int cursor = 0;
        do {
            char chr = lineScore.charAt(cursor);
            if (chr == '(') {
                int index = lineScore.indexOf(')', cursor);
                ret.add(Integer.valueOf(lineScore.substring(chr + 1, index)));
                cursor = index;
            } else if (chr != 'x') {
                ret.add(Integer.valueOf("" + chr));
            }
            cursor++;
        } while (cursor < lineScore.length());
        return ret;
    }
    
    private GameUtils() {        
    }
}
