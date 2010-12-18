package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Slugging Percentage",
        abbreviations = "SLG",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                TotalBases.class,
                AtBats.class
        })
public class SluggingPercentage extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        int ab = getDependency(AtBats.class);
        if (ab == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(getDependency(TotalBases.class)).divide(
                new BigDecimal(ab));
    }

}
