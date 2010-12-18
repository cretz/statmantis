package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Home Runs",
        abbreviations = "HR",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class HomeRuns extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int homeRuns = 0;

    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.HOME_RUN) {
            homeRuns++;
        }
    }
    
    @Override
    public Integer getCurrentValue() {
        return homeRuns;
    }
}
