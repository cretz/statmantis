package org.statmantis.stat.common;

import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.stat.BatterBasedStatistic;
import org.statmantis.stat.pitching.PitcherBasedPlayStatistic;

public abstract class PitcherAndBatterBasedPlayStatistic<T extends Number> 
        extends PitcherBasedPlayStatistic<T> implements BatterBasedStatistic {
    
    protected Person batter;
    
    @Override
    public void setCurrentBatter(Person batter) {
        this.batter = batter;
    }
    
    protected boolean isPlayApplicable(Play play) {
        return super.isPlayApplicable(play) && 
                (batter == null || batter.equals(play.getBatter().getPlayer()));
    }
}
