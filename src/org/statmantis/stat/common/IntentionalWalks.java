package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Intentional Walks",
        abbreviations = "IW",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class IntentionalWalks extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int intentionalWalks = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.INTENTIONAL_WALK) {
            intentionalWalks++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return intentionalWalks;
    }

}
