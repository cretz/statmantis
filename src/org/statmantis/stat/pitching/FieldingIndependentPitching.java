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
package org.statmantis.stat.pitching;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.common.BasesOnBalls;
import org.statmantis.stat.common.HitByPitches;
import org.statmantis.stat.common.HomeRuns;
import org.statmantis.stat.common.IntentionalWalks;
import org.statmantis.stat.common.Strikeouts;

@StatisticInfo(
        name = "Fielding Independent Pitching",
        abbreviations = "FIP",
        types = StatisticType.PITCHING,
        dependencies = {
                HomeRuns.class,
                BasesOnBalls.class,
                HitByPitches.class,
                IntentionalWalks.class,
                Strikeouts.class,
                InningsPitched.class
        })
public class FieldingIndependentPitching extends AbstractStatistic<BigDecimal> {

    //TODO: be smarter about this please and figure out how to determine this
    //  constant
    public static final BigDecimal CONSTANT = new BigDecimal("3.2");
    
    @Override
    public BigDecimal getCurrentValue() {
        //(HR*13+(BB+HBP-IBB)*3-K*2)/IP + CONSTANT
        BigDecimal ip = getDependency(InningsPitched.class);
        if (ip.compareTo(BigDecimal.ZERO) == 0) {
            return CONSTANT;
        }
        return new BigDecimal(
                (getDependency(HomeRuns.class) * 13) +
                ((getDependency(BasesOnBalls.class) +
                        getDependency(HitByPitches.class) -
                        getDependency(IntentionalWalks.class)) * 3) -
                (getDependency(Strikeouts.class) * 2)).divide(ip).add(CONSTANT);
    }

}
