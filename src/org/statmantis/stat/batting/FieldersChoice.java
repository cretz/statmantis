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
package org.statmantis.stat.batting;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

/**
 * This statistic calculates all plays for a batter that are considered
 * Fielder's Choices (FC). See the Wikipedia reference
 * <a href="http://en.wikipedia.org/wiki/Fielder%27s_choice">here</a>. This
 * is a batting statistic only.
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Fielder's Choice",
        abbreviations = "FC",
        types = StatisticType.BATTING)
public class FieldersChoice extends BatterBasedPlayStatistic<Integer> {

    private int fieldersChoice = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.FIELDERS_CHOICE) {
            fieldersChoice++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return fieldersChoice;
    }

}
