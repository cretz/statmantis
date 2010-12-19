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
package org.statmantis.stat.common.baseruns;

import java.math.BigDecimal;

import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.common.AtBats;
import org.statmantis.stat.common.BasesOnBalls;
import org.statmantis.stat.common.Hits;
import org.statmantis.stat.common.HomeRuns;
import org.statmantis.stat.common.TotalBases;

@StatisticInfo(
        name = "Base Runs (basic)",
        abbreviations = { "BR", "BsR" },
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Hits.class,
                BasesOnBalls.class,
                HomeRuns.class,
                TotalBases.class,
                AtBats.class
        })
public class BaseRunsBasic extends BaseRuns {

    @Override
    protected BigDecimal getBaserunners() {
        return new BigDecimal(getDependency(Hits.class) +
                getDependency(BasesOnBalls.class) -
                getDependency(HomeRuns.class));
    }

    @Override
    protected BigDecimal getAdvancement() {
        //(1.4*TB - .6*H - 3*HR + .1*W)*1.02
        BigDecimal ret = new BigDecimal("1.4").
                multiply(new BigDecimal(getDependency(TotalBases.class)));
        ret.subtract(new BigDecimal("0.6").multiply(
                new BigDecimal(getDependency(Hits.class))));
        ret.subtract(new BigDecimal(3 * getDependency(HomeRuns.class)));
        ret.add(new BigDecimal("0.1").multiply(
                new BigDecimal(getDependency(BasesOnBalls.class))));
        return ret.multiply(new BigDecimal("1.02"));
    }

    @Override
    protected BigDecimal getOuts() {
        return new BigDecimal(getDependency(AtBats.class) -
                getDependency(Hits.class));
    }

    @Override
    protected BigDecimal getGuaranteedRuns() {
        return new BigDecimal(getDependency(HomeRuns.class));
    }

}
