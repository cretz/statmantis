package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Doubles",
        abbreviations = "2B",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class Doubles extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int doubles = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && (play.getEvent().
                getPlayType() == PlayType.DOUBLE || play.getEvent().
                getPlayType() == PlayType.GROUND_RULE_DOUBLE)) {
            doubles++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return doubles;
    }

}
