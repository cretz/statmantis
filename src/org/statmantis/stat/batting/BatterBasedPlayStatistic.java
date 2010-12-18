package org.statmantis.stat.batting;

import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.BatterBasedStatistic;
import org.statmantis.stat.PlayBasedStatistic;

public abstract class BatterBasedPlayStatistic<T extends Number> 
        extends AbstractStatistic<T> 
        implements PlayBasedStatistic, BatterBasedStatistic {

    protected Person batter;

    @Override
    public void setCurrentBatter(Person batter) {
        this.batter = batter;
    }
    
    protected boolean isPlayApplicable(Play play) {
        return batter == null || batter.equals(play.getPitcher().getPlayer());
    }
}
