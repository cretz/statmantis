package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Ground Outs",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class GroundOuts extends PitcherAndBatterBasedPlayStatistic<Integer> {

    @Override
    public void applyPlay(Play play) {
        //TODO: figure out what constitutes a ground out...
        //  error? sac? fc?
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer getCurrentValue() {
        throw new UnsupportedOperationException();
    }

}
