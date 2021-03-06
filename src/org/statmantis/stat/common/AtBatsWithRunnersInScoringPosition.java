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
package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

/**
 * Same as {@link AtBats} except only with runners in scoring position (AB/RSP).
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "At Bats with Runners in Scoring Position",
        abbreviations = "AB/RSP",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class AtBatsWithRunnersInScoringPosition extends AtBats {

    @Override
    protected boolean isQualifiedAtBat(Play play) {
        return (play.getRunnerAtSecond() != null && play.getRunnerAtThird() != null) &&
                super.isQualifiedAtBat(play);
    }
}
