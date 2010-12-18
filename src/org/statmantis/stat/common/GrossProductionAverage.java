package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Gross Production Average",
        abbreviations = "GPA",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                OnBasePercentage.class,
                SluggingPercentage.class
        })
public class GrossProductionAverage extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        return new BigDecimal("1.8").multiply(getDependency(
                OnBasePercentage.class)).add(getDependency(SluggingPercentage.class)).
                divide(new BigDecimal(4));
    }

}
