package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "On-base plus Slugging Percentage",
        abbreviations = "OPS",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                OnBasePercentage.class,
                SluggingPercentage.class
        })
public class OnBasePlusSlugging extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        return getDependency(OnBasePercentage.class).add(
                getDependency(SluggingPercentage.class));
    }

}
