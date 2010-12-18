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
