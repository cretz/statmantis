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
package org.statmantis.stat.common.fielding;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.common.BasesOnBalls;
import org.statmantis.stat.common.HitByPitches;
import org.statmantis.stat.common.Hits;
import org.statmantis.stat.common.HomeRuns;
import org.statmantis.stat.common.Strikeouts;
import org.statmantis.stat.pitching.BattersFaced;

@StatisticInfo(
        name = "Defense Effeciency Ratio (THT)",
        abbreviations = "DER",
        types = StatisticType.FIELDING,
        dependencies = {
                BattersFaced.class,
                Hits.class,
                Strikeouts.class,
                BasesOnBalls.class,
                HitByPitches.class,
                BattedBallErrors.class,
                HomeRuns.class
        })
public class DefenseEffeciencyRatioTht extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        //(BFP-H-K-BB-HBP-Errors)/(BFP-HR-K-BB-HBP)
        int bfp = getDependency(BattersFaced.class);
        int k = getDependency(Strikeouts.class);
        int bb = getDependency(BasesOnBalls.class);
        int hbp = getDependency(HitByPitches.class);
        int denom = bfp - getDependency(HomeRuns.class) - k - bb - hbp;
        if (denom == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(bfp - getDependency(Hits.class) - k -
                bb - hbp - getDependency(HitByPitches.class)).divide(
                        new BigDecimal(denom));
    }

}
