package org.statmantis.stat.common;

import java.util.Set;

import org.statmantis.model.Play;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayType;
import org.statmantis.model.Team;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.TeamBasedStatistic;

import com.google.common.collect.Sets;

@StatisticInfo(
        name = "Left On Base",
        abbreviations = "LOB",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class LeftOnBase extends PitcherAndBatterBasedPlayStatistic<Integer> 
        implements TeamBasedStatistic {

    @Override
    public void setCurrentTeam(Team team) {
        //TODO: revisit this when I answer these questions...
        //  1. Is individual batter LOB counted during errors?
        //  2. Are pitcher LOB numbers only calc'd at the end 
        //      of the half inning like team LOB?
    }

    @Override
    public void applyPlay(Play play) {
    }

    @Override
    public Integer getCurrentValue() {
        throw new UnsupportedOperationException();
    }
}
