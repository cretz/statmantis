package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Singles",
        abbreviations = "1B",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class Singles extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int singles = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.SINGLE) {
            singles++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return singles;
    }

}
