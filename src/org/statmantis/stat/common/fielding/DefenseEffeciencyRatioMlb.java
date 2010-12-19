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
        name = "Defense Effeciency Ratio (mlb.com)",
        abbreviations = "DER",
        types = StatisticType.FIELDING,
        dependencies = {
                Hits.class,
                HomeRuns.class,
                BattersFaced.class,
                BasesOnBalls.class,
                HitByPitches.class,
                Strikeouts.class
        })
public class DefenseEffeciencyRatioMlb extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        int hr = getDependency(HomeRuns.class);
        BigDecimal denom = new BigDecimal(
                getDependency(BattersFaced.class) -
                hr -
                getDependency(BasesOnBalls.class) -
                getDependency(HitByPitches.class) -
                getDependency(Strikeouts.class));
        if (denom.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        BigDecimal num = new BigDecimal(
                getDependency(Hits.class) - hr);
        return BigDecimal.ONE.subtract(num.divide(denom));
    }

}
