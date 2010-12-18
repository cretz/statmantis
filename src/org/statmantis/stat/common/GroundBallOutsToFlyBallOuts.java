package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Ground Ball Outs to Fly Ball Outs",
        abbreviations = "GO/AO",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                GroundOuts.class,
                FlyOuts.class
        })
public class GroundBallOutsToFlyBallOuts extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        //TODO: do this when you can do ground outs and fly outs
        throw new UnsupportedOperationException();
    }

}
