package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Batting Average",
        abbreviations = { "BA", "AVG" },
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Hits.class,
                AtBats.class
        })
public class BattingAverage extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        int atBats = getDependency(AtBats.class);
        if (atBats == 0) {
            return BigDecimal.ZERO;
        }
        int hits = getDependency(Hits.class);
        return new BigDecimal(hits).divide(new BigDecimal(atBats));
    }

}
