package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Bases on Balls",
        abbreviations = "BB",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class BasesOnBalls extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int basesOnBalls = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && (play.getEvent().getPlayType() == PlayType.WALK || 
                play.getEvent().getPlayType() == PlayType.INTENTIONAL_WALK)) {
            basesOnBalls++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return basesOnBalls;
    }

}
