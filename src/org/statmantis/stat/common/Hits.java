package org.statmantis.stat.common;

import java.util.Set;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

import com.google.common.collect.Sets;

@StatisticInfo(
        name = "Hits",
        abbreviations = "H",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class Hits extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private static final Set<PlayType> VALID_PLAY_TYPES =
        Sets.newHashSet(
                PlayType.SINGLE,
                PlayType.DOUBLE,
                PlayType.TRIPLE,
                PlayType.GROUND_RULE_DOUBLE,
                PlayType.HOME_RUN);

    private int hits = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && VALID_PLAY_TYPES.contains(
                play.getEvent().getPlayType())) {
            hits++;
        }
    }
    
    @Override
    public Integer getCurrentValue() {
        return hits;
    }
}
