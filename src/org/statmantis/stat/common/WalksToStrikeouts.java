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
