package org.statmantis.stat.common;

import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "At Bats",
        abbreviations = "AB",
        types = { StatisticType.BATTING, StatisticType.PITCHING },
        dependencies = {
                Singles.class,
                Doubles.class,
                Triples.class,
                HomeRuns.class
        })
public class TotalBases extends AbstractStatistic<Integer> {

    @Override
    public Integer getCurrentValue() {
        int ret = getDependency(Singles.class);
        ret += getDependency(Doubles.class) * 2;
        ret += getDependency(Triples.class) * 3;
        ret += getDependency(HomeRuns.class) * 4;
        return ret;
    }

}
