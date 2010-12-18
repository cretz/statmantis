package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Strikeouts",
        abbreviations = "K",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class Strikeouts extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int strikeouts = 0;

    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.STRIKE_OUT) {
            strikeouts++;
        }
    }
    
    @Override
    public Integer getCurrentValue() {
        return strikeouts;
    }
}
