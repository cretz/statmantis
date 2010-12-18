package org.statmantis.stat.common;

import java.util.Set;

import org.statmantis.model.Play;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

import com.google.common.collect.Sets;

@StatisticInfo(
        name = "At Bats",
        abbreviations = "AB",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class AtBats extends PitcherAndBatterBasedPlayStatistic<Integer> {

    static final Set<PlayType> INVALID_PLAY_TYPES =
        Sets.newHashSet(
                PlayType.HIT_BY_PITCH,
                PlayType.INTENTIONAL_WALK,
                PlayType.WALK,
                PlayType.BALK,
                PlayType.INTERFERENCE,
                PlayType.ERROR_FOUL_FLY,
                PlayType.CAUGHT_STEALING,
                PlayType.DEFENSIVE_INDIFFERENCE,
                PlayType.RUNNER_ADVANCEMENT,
                PlayType.PASSED_BALL,
                PlayType.WILD_PITCH,
                PlayType.PICKOFF,
                PlayType.PICKOFF_CAUGHT_STEALING,
                PlayType.STOLEN_BASE);
    
    private int atBats = 0;
    
    protected boolean isQualifiedAtBat(Play play) {
        //me?
        if (isPlayApplicable(play)) {
            return false;
        }
        //must be a batter involved qualifying play
        if (INVALID_PLAY_TYPES.contains(play.getEvent().getPlayType())) {
            return false;
        }
        //no sacs
        if (play.getEvent().getModifiers() != null) {
            for (PlayModifier modifier : play.getEvent().getModifiers()) {
                if (modifier.getType() == PlayModifierType.SACRIFICE_FLY ||
                        modifier.getType() == PlayModifierType.SACRIFICE_HIT) {
                    return false;
                }
            }
        }
        //TODO: handle the issue where there was two strikes and 
        //  he was replaced and the other batter struck out
        return true;
    }

    @Override
    public void applyPlay(Play play) {
        if (isQualifiedAtBat(play)) {
            atBats++;
        }
    }
    
    @Override
    public Integer getCurrentValue() {
        return atBats;
    }

}
