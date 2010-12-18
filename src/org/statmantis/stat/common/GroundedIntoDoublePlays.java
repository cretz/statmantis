package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Grounded Into Double Plays",
        abbreviations = { "GDP", "GIDP" },
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class GroundedIntoDoublePlays extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int groundedIntoDoublePlays = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.GROUND_OUT_DOUBLE_PLAY) {
            groundedIntoDoublePlays++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return groundedIntoDoublePlays;
    }

}
