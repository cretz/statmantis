/*
 * Copyright 2010 Chad Retz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.statmantis.mport.retro.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statmantis.model.AtBatEvent;
import org.statmantis.model.GamePlayer;
import org.statmantis.model.GamePosition;
import org.statmantis.model.HandPreference;
import org.statmantis.model.Play;
import org.statmantis.model.PlayRunnerAdvancement;
import org.statmantis.model.Position;
import org.statmantis.mport.retro.RetrosheetRuntimeException;

/**
 * Utilities for parsing game lines
 * 
 * @author Chad Retz
 */
final class EventFileUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(EventFileUtils.class);

    /**
     * Parse a line of an event file
     * 
     * @param line
     * @param context
     * @see <a href="http://www.retrosheet.org/eventfile.htm">Retrosheet Reference</a>
     */
    public static void parseLine(String[] line, GameContext context) {
        String discriminator = line[0];
        if ("id".equals(discriminator)) {
            context.newGame(line[1]);
        } else if ("version".equals(discriminator)) {
            //who cares...
        } else if ("info".equals(discriminator)) {
            parseInformation(line, context);
        } else if ("start".equals(discriminator) || "sub".equals(discriminator)) {
            parseStartSub(line, context);
        } else if ("com".equals(discriminator)) {
            parseComment(line, context);
        } else if ("play".equals(discriminator)) {
            parsePlay(line, context);
        } else if ("padj".equals(discriminator) || "badj".equals(discriminator)) {
            parseBatterPitcherAdjustment(line, context, "badj".equals(discriminator));
        } else if ("ladj".equals(discriminator)) {
            parseBattedOutOfTurn(line, context);
        } else {
            throw new RetrosheetRuntimeException("Unrecognized discriminator: " + 
                    discriminator);
        }
    }
    
    /**
     * Parse an information line in an event file
     * 
     * @param line
     * @param context
     * @see <a href="http://www.retrosheet.org/eventfile.htm#1">Retrosheet Reference</a>
     */
    public static void parseInformation(String[] line, GameContext context) {
        InformationRecord record = InformationRecord.RECORDS.get(line[1]);
        if (record == null) {
            throw new RetrosheetRuntimeException("Unknown info record " + line[1]);
        }
        record.populate(line[2], context);
    }
    
    /**
     * Parse a start/sub line in an event file
     * 
     * @param line
     * @param context
     * @see <a href="http://www.retrosheet.org/eventfile.htm">Retrosheet Reference</a>
     */
    public static void parseStartSub(String[] line, GameContext context) {
        GamePlayer player = context.getCurrentPlayers().get(line[1]);
        if (player == null) {
            //haven't seen this guy before...
            player = new GamePlayer();
            player.setPlayer(context.getRetroContext().getPersons().get(line[1]));
            if (player.getPlayer() == null) {
                throw new RetrosheetRuntimeException("Unknown player " + line[1]);
            }
            if ("0".equals(line[3])) {
                context.getCurrentGame().getVisitorInfo().getGamePlayers().add(player);
            } else {
                context.getCurrentGame().getHomeInfo().getGamePlayers().add(player);
            }
            context.getCurrentPlayers().put(player.getPlayer().getRetroId(), player);
        }
        //build up his position
        GamePosition position = new GamePosition();
        position.setBeginningInning(context.getCurrentInning());
        if (!"0".equals(line[4])) {
            position.setLineupPosition(Integer.valueOf(line[4]));
        }
        position.setPosition(Position.fromRetroId(
                Integer.valueOf(line[5])));
        if (player.getPositions() == null) {
            player.setPositions(new ArrayList<GamePosition>(
                    Collections.singleton(position)));
        } else {
            player.getPositions().add(position);
        }
        if ("sub".equals(line[0])) {
            //a substitution is also a play
            Play play = new Play();
            play.setHomeTeam(!"0".equals(line[3]));
            play.setInning(context.getCurrentInning());
            play.setPlayer(player);
            play.setSubstitution(true);
            context.getCurrentGame().getPlays().add(play);
        }
    }

    /**
     * Parse a comment line in an event file
     * 
     * @param line
     * @param context
     * @see <a href="http://www.retrosheet.org/eventfile.htm">Retrosheet Reference</a>
     */
    public static void parseComment(String[] line, GameContext context) {
        //a comment to be added to the previous play
        if (!context.getCurrentGame().getPlays().isEmpty()) {
            Play lastPlay = context.getCurrentGame().getPlays().get(
                    context.getCurrentGame().getPlays().size() - 1);
            List<String> comments = lastPlay.getComments();
            if (comments == null) {
                comments = new ArrayList<String>();
                lastPlay.setComments(comments);
            }
            comments.add(line[1]);
        } else if (logger.isWarnEnabled()) {
            logger.warn("Comment '" + line[1] + "' can't be added to game " +
                    context.getCurrentGame() + " because it has no plays");
        }
    }

    /**
     * Parse a play line in an event file
     * 
     * @param line
     * @param context
     * @see <a href="http://www.retrosheet.org/eventfile.htm#5">Retrosheet Reference</a>
     */
    public static void parsePlay(String[] line, GameContext context) {
        //the holy grail...a play record
        Play play = new Play();
        //simple stuff
        play.setInning(Integer.valueOf(line[1]));
        play.setHomeTeam(!"0".equals(line[2]));
        play.setPlayer(context.getCurrentPlayers().get(line[3]));
        if (play.getPlayer() == null) {
            throw new RetrosheetRuntimeException("Couldn't find player " + line[3]);
        }
        if (!"??".equals(line[4])) {
            //the count if present
            play.setBalls(Integer.valueOf(line[4].charAt(0)));
            play.setStrikes(Integer.valueOf(line[4].charAt(1)));
        }
        if (!line[5].isEmpty()) {
            //at bat events
            play.setAtBatEvents(new ArrayList<AtBatEvent>(line[5].length()));
            for (int j = 0; j < line[5].length(); j++) {
                AtBatEvent event = AtBatEvent.EVENTS.get(String.valueOf(
                        line[5].charAt(j)));
                if (event == null) {
                    throw new RetrosheetRuntimeException(
                            "Unrecognized AB event " + line[5].charAt(j));
                }
                play.getAtBatEvents().add(event);
            }
        }
        if (!line[6].isEmpty()) {
            //split it in to pieces
            int periodIndex = line[6].indexOf('.');
            String event;
            if (periodIndex != -1) {
                play.setAdvancements(new ArrayList<PlayRunnerAdvancement>());
                //separated by semicolons
                for (String adv : line[6].substring(periodIndex + 1).split(";")) {
                    play.getAdvancements().add(PlayUtils.buildPlayRunnerAdvancement(adv, context));
                }
                event = line[6].substring(0, periodIndex);
            } else {
                event = line[6];
            }
            play.setEvent(PlayUtils.buildPlayEvent(event, context));
            //TODO: update the damn context w/ base runners and such
        } else {
            throw new RetrosheetRuntimeException("Play is empty");
        }
        context.getCurrentGame().getPlays().add(play);
    }
    
    public static void parseBatterPitcherAdjustment(String[] line, 
            GameContext context, boolean batter) {
        Play play = new Play();
        play.setPlayer(context.getCurrentPlayers().get(line[1]));
        if (batter) {
            play.setBatterHandAdjustment("R".equals(line[2]) ? 
                    HandPreference.RIGHT : HandPreference.LEFT);
        } else {
            play.setPitcherHandAdjustment("R".equals(line[2]) ? 
                    HandPreference.RIGHT : HandPreference.LEFT);
        }
        context.getCurrentGame().getPlays().add(play);
    }
    
    public static void parseBattedOutOfTurn(String[] line, GameContext context) {
        Play play = new Play();
        play.setBattedOutOfTurn(true);
        boolean home = "0".equals(line[1]);
        int properPosition = Integer.parseInt(line[2]);
        //find that player!
        for (GamePlayer player : (home ? 
                context.getCurrentGame().getHomeInfo().getGamePlayers() :
                    context.getCurrentGame().getVisitorInfo().getGamePlayers())) {
            //check last position
            Integer lineupPosition = player.getPositions().get(
                    player.getPositions().size() - 1).getLineupPosition();
            if (lineupPosition != null && properPosition == lineupPosition) {
                play.setPlayer(player);
                context.getCurrentGame().getPlays().add(play);
                return;
            }
        }
        //AHH!
        throw new RetrosheetRuntimeException(
                "Unable to find player at position for batting out of turn");
    }
    
    private EventFileUtils() {
    }
}
