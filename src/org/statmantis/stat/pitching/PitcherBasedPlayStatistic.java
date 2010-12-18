package org.statmantis.stat.pitching;

import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.PitcherBasedStatistic;
import org.statmantis.stat.PlayBasedStatistic;

public abstract class PitcherBasedPlayStatistic<T extends Number> 
        extends AbstractStatistic<T> 
        implements PlayBasedStatistic, PitcherBasedStatistic {

    protected Person pitcher;

    @Override
    public void setCurrentPitcher(Person pitcher) {
        this.pitcher = pitcher;
    }
    
    protected boolean isPlayApplicable(Play play) {
        return pitcher == null || pitcher.equals(play.getPitcher().getPlayer());
    }
}
