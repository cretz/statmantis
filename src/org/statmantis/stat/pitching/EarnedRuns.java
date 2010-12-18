package org.statmantis.stat.pitching;

import java.util.ArrayList;
import java.util.List;

import org.statmantis.model.Game;
import org.statmantis.model.GamePlayer;
import org.statmantis.model.Person;
import org.statmantis.model.Team;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.GameBasedStatistic;
import org.statmantis.stat.PitcherBasedStatistic;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.TeamBasedStatistic;

@StatisticInfo(
        name = "Earned Runs",
        abbreviations = "ER",
        types = StatisticType.PITCHING)
public class EarnedRuns extends AbstractStatistic<Integer>
        implements GameBasedStatistic, PitcherBasedStatistic, TeamBasedStatistic {

    private int earnedRuns = 0;
    private Team team;
    private Person pitcher;
    
    @Override
    public void setCurrentTeam(Team team) {
        this.team = team;
    }
    
    @Override
    public void setCurrentPitcher(Person pitcher) {
        this.pitcher = pitcher;
    }
    
    @Override
    public void applyGame(Game game) {
        List<GamePlayer> applicablePlayers = new ArrayList<GamePlayer>();
        //certain team?
        if (team == null || team.equals(game.getHomeTeam())) {
            applicablePlayers.addAll(game.getHomeInfo().getGamePlayers());
        }
        if (team == null || team.equals(game.getVisitingTeam())) {
            applicablePlayers.addAll(game.getVisitorInfo().getGamePlayers());
        }
        for (GamePlayer player : applicablePlayers) {
            if (player.getEarnedRuns() != null && 
                    (pitcher == null || pitcher.equals(player.getPlayer()))) {
                earnedRuns += player.getEarnedRuns();
            }
        }
    }

    @Override
    public Integer getCurrentValue() {
        return earnedRuns;
    }

}
