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

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

/**
 * This calculates the Batting Average with Runners in Scoring Position (BA/RSP).
 * This is a batting and pitching statistic. It is calculated as:
 * <p>
 * <math style="font-size: 200%">
 *     <mfrac>
 *         <mi href="{@linkhref Hits}">H</mi>
 *         <mi href="{@linkhref AtBatsWithRunnersInScoringPosition}">AB/RSP</mi>
 *     </mfrac>
 * </math>
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Batting Average with Runners in Scoring Position",
        abbreviations = "BA/RSP",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Hits.class,
                AtBatsWithRunnersInScoringPosition.class
        })
public class BattingAverageWithRunnersInScoringPosition extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        int atBats = getDependency(AtBatsWithRunnersInScoringPosition.class);
        if (atBats == 0) {
            return BigDecimal.ZERO;
        }
        int hits = getDependency(Hits.class);
        return new BigDecimal(hits).divide(new BigDecimal(atBats));
    }

}
