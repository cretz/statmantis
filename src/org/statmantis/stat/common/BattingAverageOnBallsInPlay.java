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
 * This calculates Batting Average on Balls In Play (BABIP). See the Wikipedia reference
 * <a href="http://en.wikipedia.org/wiki/Batting_average_on_balls_in_play">here</a>. 
 * This is a pitching and batting statistic. This is calculated as:
 * <p>
 * <math style="font-size: 200%">
 *     <mfrac>
 *         <mrow>
 *             <mi href="{@linkhref Hits}">H</mi>
 *             <mo>-</mo>
 *             <mi href="{@linkhref HomeRuns}">HR</mi>
 *         </mrow>
 *         <mrow>
 *             <mi href="{@linkhref AtBats}">AB</mi>
 *             <mo>-</mo>
 *             <mi href="{@linkhref Strikeouts}">K</mi>
 *             <mo>-</mo>
 *             <mi href="{@linkhref HomeRuns}">HR</mi>
 *             <mo>+</mo>
 *             <mi href="{@linkhref SacrificeFlies}">SF</mi>
 *         </mrow>
 *     </mfrac>
 * </math>
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Batting Average on Balls in Play",
        abbreviations = "BABIP",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Hits.class,
                HomeRuns.class,
                AtBats.class,
                Strikeouts.class,
                SacrificeFlies.class
        })
public class BattingAverageOnBallsInPlay extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        int atBats = getDependency(AtBats.class);
        int strikeouts = getDependency(Strikeouts.class);
        int homeRuns = getDependency(HomeRuns.class);
        int sacrificeFlies = getDependency(SacrificeFlies.class);
        int denom = atBats - strikeouts - homeRuns + sacrificeFlies;
        if (denom == 0) {
            return BigDecimal.ZERO;
        }
        int hits = getDependency(Hits.class);
        return new BigDecimal(hits - homeRuns).divide(new BigDecimal(denom));
    }

}
