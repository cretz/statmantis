package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Hit by Pitches",
        abbreviations = "HBP",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class HitByPitches extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int hitByPitches = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.HIT_BY_PITCH) {
            hitByPitches++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return hitByPitches;
    }

}
