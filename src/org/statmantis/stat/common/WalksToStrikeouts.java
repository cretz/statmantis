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

@StatisticInfo(
        name = "Walks to Strikeouts",
        abbreviations = "BB/K",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                BasesOnBalls.class,
                Strikeouts.class
        })
public class WalksToStrikeouts extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        int strikeouts = getDependency(Strikeouts.class);
        if (strikeouts == 0) {
            return BigDecimal.ZERO;
        }
        int walks = getDependency(BasesOnBalls.class);
        return new BigDecimal(walks).divide(new BigDecimal(strikeouts));
    }

}
