package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "On-base Percentage",
        abbreviations = { "OBP", "OBA" },
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Hits.class,
                BasesOnBalls.class,
                HitByPitches.class,
                AtBats.class,
                SacrificeFlies.class
        })
public class OnBasePercentage extends AbstractStatistic<BigDecimal> {

    @Override
    public BigDecimal getCurrentValue() {
        int h = getDependency(Hits.class);
        int bb = getDependency(BasesOnBalls.class);
        int hbp = getDependency(HitByPitches.class);
        int ab = getDependency(AtBats.class);
        int sf = getDependency(SacrificeFlies.class);
        int denom = ab + bb + hbp + sf;
        if (denom == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(h + bb + hbp).divide(
                new BigDecimal(denom));
    }

}
