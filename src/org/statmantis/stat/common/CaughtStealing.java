package org.statmantis.stat.common;

import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.CatcherBasedStatistic;
import org.statmantis.stat.PitcherBasedStatistic;
import org.statmantis.stat.PlayBasedStatistic;
import org.statmantis.stat.RunnerBasedStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.StatisticUtils;

@StatisticInfo(
        name = "Caught Stealing",
        abbreviations = "CS",
        types = { StatisticType.BATTING, StatisticType.PITCHING, 
                  StatisticType.FIELDING, StatisticType.RUNNING })
public class CaughtStealing extends AbstractStatistic<Integer> implements PlayBasedStatistic, 
        PitcherBasedStatistic, RunnerBasedStatistic, CatcherBasedStatistic {

    private int caughtStealing = 0;
    private Person runner;
    private Person pitcher;
    private Person catcher;
    
    @Override
    public void setCurrentRunner(Person runner) {
        this.runner = runner;
    }

    @Override
    public void setCurrentPitcher(Person pitcher) {
        this.pitcher = pitcher;
    }
    
    @Override
    public void setCurrentCatcher(Person catcher) {
        this.catcher = catcher;
    }

    @Override
    public void applyPlay(Play play) {
        //is caught stealing?
        if (play.getEvent().getPlayType() != PlayType.CAUGHT_STEALING &&
                play.getEvent().getPlayType() != PlayType.PICKOFF_CAUGHT_STEALING) {
            return;
        }
        //is runner?
        if (runner != null && !runner.equals(play.getPlayer().getPlayer())) {
            return;
        }
        //is pitcher?
        if (pitcher != null && !pitcher.equals(play.getPitcher().getPlayer())) {
            return;
        }
        //is catcher?
        if (catcher != null && !StatisticUtils.isCatcherInPlay(catcher, play)) {
            return;
        }
        caughtStealing++;
    }
    
    @Override
    public Integer getCurrentValue() {
        return caughtStealing;
    }

}
