package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "At Bats with Runners in Scoring Position",
        abbreviations = "AB/RSP",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class AtBatsWithRunnersInScoringPosition extends AtBats {

    @Override
    protected boolean isQualifiedAtBat(Play play) {
        return (play.getRunnerAtSecond() != null && play.getRunnerAtThird() != null) &&
                super.isQualifiedAtBat(play);
    }
}
