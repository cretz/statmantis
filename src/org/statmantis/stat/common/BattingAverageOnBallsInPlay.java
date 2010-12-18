package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Batting Average on Balls in Play",
        abbreviations = "BABIP",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Hits.class,
                HomeRuns.class,
                AtBats.class,
                Strikeouts.class,
                SacrificeFlies.class
        })
public class BattingAverageOnBallsInPlay extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        int atBats = getDependency(AtBats.class);
        int strikeouts = getDependency(Strikeouts.class);
        int homeRuns = getDependency(HomeRuns.class);
        int sacrificeFlies = getDependency(SacrificeFlies.class);
        int denom = atBats - strikeouts - homeRuns + sacrificeFlies;
        if (denom == 0) {
            return BigDecimal.ZERO;
        }
        int hits = getDependency(Hits.class);
        return new BigDecimal(hits - homeRuns).divide(new BigDecimal(denom));
    }

}
