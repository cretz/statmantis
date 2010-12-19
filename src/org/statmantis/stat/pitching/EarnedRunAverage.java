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

@StatisticInfo(
        name = "Earned Run Average",
        abbreviations = "ERA",
        types = StatisticType.PITCHING,
        dependencies = {
                InningsPitched.class,
                EarnedRuns.class
        })
public class EarnedRunAverage extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        BigDecimal inningsPitched = getDependency(InningsPitched.class);
        if (inningsPitched.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        int earnedRuns = getDependency(EarnedRuns.class);
        return new BigDecimal(earnedRuns).divide(inningsPitched).multiply(NINE);
    }

}
