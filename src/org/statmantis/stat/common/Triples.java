package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Triples",
        abbreviations = "3B",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class Triples extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int triples = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.TRIPLE) {
            triples++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return triples;
    }

}
