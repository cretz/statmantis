package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Base Runs (extra)",
        abbreviations = { "BR", "BsR" },
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Hits.class,
                BasesOnBalls.class,
                HitByPitches.class,
                HomeRuns.class,
                IntentionalWalks.class,
                TotalBases.class,
                StolenBases.class,
                CaughtStealing.class,
                GroundedIntoDoublePlays.class,
                AtBats.class
        })
public class BaseRunsExtra extends BaseRunsBasic {

    @Override
    protected BigDecimal getBaserunners() {
        //H + W + HBP - HR - .5*IW
        BigDecimal ret = new BigDecimal(getDependency(Hits.class) +
                getDependency(BasesOnBalls.class) +
                getDependency(HitByPitches.class) -
                getDependency(HomeRuns.class));
        return ret.subtract(new BigDecimal("0.5").multiply(
                new BigDecimal(getDependency(IntentionalWalks.class))));
    }

    @Override
    protected BigDecimal getAdvancement() {
        //(1.4*TB - .6*H - 3*HR + .1*(W + HBP - IW) + .9*(SB - CS - GDP))*1.1
        BigDecimal ret = new BigDecimal("1.4").
                multiply(new BigDecimal(getDependency(TotalBases.class)));
        ret.subtract(new BigDecimal("0.6").multiply(
                new BigDecimal(getDependency(Hits.class))));
        ret.subtract(new BigDecimal(3 * getDependency(HomeRuns.class)));
        ret.add(new BigDecimal("0.1").multiply(new BigDecimal(
                getDependency(BasesOnBalls.class) +
                getDependency(HitByPitches.class) -
                getDependency(IntentionalWalks.class))));
        ret.add(new BigDecimal("0.9").multiply(new BigDecimal(
                getDependency(StolenBases.class) -
                getDependency(CaughtStealing.class) -
                getDependency(GroundedIntoDoublePlays.class))));
        return ret.multiply(new BigDecimal("1.1"));
    }

    @Override
    protected BigDecimal getOuts() {
        //AB - H + CS + GDP
        return new BigDecimal(
                getDependency(AtBats.class) -
                getDependency(Hits.class) +
                getDependency(CaughtStealing.class) +
                getDependency(GroundedIntoDoublePlays.class));
    }

}
