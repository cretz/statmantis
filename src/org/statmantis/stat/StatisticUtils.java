package org.statmantis.stat;

import java.util.List;

import org.statmantis.model.GamePlayer;
import org.statmantis.model.GamePosition;
import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayType;
import org.statmantis.model.Position;
import org.statmantis.model.Team;

public class StatisticUtils {

    //TODO: move this to a more general place
    
    public static boolean isTeamOnField(Team team, Play play) {
        return (play.isHomeTeam() && play.getGame().getVisitingTeam().equals(team)) ||
                (!play.isHomeTeam() && play.getGame().getHomeTeam().equals(team));
    }
    
    public static boolean isSacrificeFly(Play play) {
        if (play.getEvent().getPlayType() == PlayType.FLY_OUT) {
            //check the modifiers
            if (play.getEvent().getModifiers() != null) {
                for (PlayModifier modifier : play.getEvent().getModifiers()) {
                    if (modifier.getType() == PlayModifierType.SACRIFICE_FLY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isSacrificeHit(Play play) {
        if (play.getEvent().getPlayType() == PlayType.GROUND_OUT) {
            //check the modifiers
            if (play.getEvent().getModifiers() != null) {
                for (PlayModifier modifier : play.getEvent().getModifiers()) {
                    if (modifier.getType() == PlayModifierType.SACRIFICE_HIT) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isSacrifice(Play play) {
        if (play.getEvent().getPlayType() == PlayType.GROUND_OUT ||
                play.getEvent().getPlayType() == PlayType.FLY_OUT) {
            //check the modifiers
            if (play.getEvent().getModifiers() != null) {
                for (PlayModifier modifier : play.getEvent().getModifiers()) {
                    if (modifier.getType() == PlayModifierType.SACRIFICE_HIT ||
                            modifier.getType() == PlayModifierType.SACRIFICE_FLY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isLoadedBases(Play play) {
        return play.getRunnerAtFirst() != null && play.getRunnerAtSecond() != null &&
            play.getRunnerAtThird() != null;
    }
    
    public static boolean isCatcherInPlay(Person catcher, Play play) {
        Position position = getCurrentFielderPosition(catcher, play);
        return position != null && position == Position.CATCHER;
    }
    
    public static Position getCurrentFielderPosition(Person fielder, Play play) {
        //TODO: heavy
        List<GamePlayer> applicablePlayers;
        if (play.isHomeTeam()) {
            applicablePlayers = play.getGame().getVisitorInfo().getGamePlayers();
        } else {
            applicablePlayers = play.getGame().getHomeInfo().getGamePlayers();
        }
        for (GamePlayer player : applicablePlayers) {
            if (fielder.equals(player.getPlayer())) {
                for (GamePosition position : player.getPositions()) {
                    if (position.getEndingInning() == null ||
                            position.getEndingInning() > play.getInning()) {
                        return position.getPosition();
                    } else if (position.getEndingInning() == play.getInning()) {
                        //check the outs
                        //TODO: so, 0-2 or 1-3?
                        int outsWhenPulled = position.getEndingInningOutCount() % 3;
                        if (outsWhenPulled > play.getInning()) {
                            return position.getPosition();
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private StatisticUtils() {
    }
}
