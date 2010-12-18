package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

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
