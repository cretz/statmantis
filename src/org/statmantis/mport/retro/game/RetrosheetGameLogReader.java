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
package org.statmantis.mport.retro.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statmantis.model.Forfeiture;
import org.statmantis.model.Game;
import org.statmantis.model.GameAccuracy;
import org.statmantis.model.GameCompletion;
import org.statmantis.model.GameDayNumber;
import org.statmantis.model.GamePlayer;
import org.statmantis.model.GamePosition;
import org.statmantis.model.Position;
import org.statmantis.model.Protest;
import org.statmantis.model.TeamGameInfo;
import org.statmantis.mport.retro.RetrosheetContext;
import org.statmantis.mport.retro.RetrosheetRuntimeException;
import org.statmantis.mport.retro.RetrosheetYearReader;
import org.statmantis.util.CsvUtils;
import org.statmantis.util.GameUtils;

/**
 * Game log reader
 *
 * @author Chad Retz
 */
@SuppressWarnings("unused")
public class RetrosheetGameLogReader extends RetrosheetYearReader<Map<String, Game>> {

    private static final Logger logger = LoggerFactory.getLogger(
            RetrosheetGameLogReader.class);
    
    private final int yearBegin;
    private final int yearEnd;
    private final String directory;
    private final RetrosheetContext retroContext;
    
    public RetrosheetGameLogReader(int yearBegin, int yearEnd, RetrosheetContext retroContext) {
        this(yearBegin, yearEnd, null, retroContext);
    }
    
    public RetrosheetGameLogReader(int yearBegin, int yearEnd, 
            String directory, RetrosheetContext retroContext) {
        this.yearBegin = yearBegin;
        this.yearEnd = yearEnd;
        this.directory = directory;
        this.retroContext = retroContext;
    }
    
    @Override
    public Map<String, Game> read() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Game> read(InputStream stream) throws IOException {
        Map<String, Game> games = new HashMap<String, Game>();
        ZipInputStream zis = new ZipInputStream(stream);
        try {
            ZipEntry entry;
            do {
                entry = zis.getNextEntry();
                if (entry == null) {
                    break;
                }
                games.putAll(readGameLog(new BufferedReader(new InputStreamReader(zis))));
            } while (true);
        } finally {
            try {
                zis.close();
            } catch (Exception e) {
                //no-op
            }
        }
        return games;
    }
    
    protected Map<String, Game> readGameLog(BufferedReader reader) throws IOException {
        List<Map<GameLogColumn, String>> lines = CsvUtils.readCsv(
                GameLogColumn.class, reader, false);
        Map<String, Game> ret = new HashMap<String, Game>();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < lines.size(); i++) {
            Map<GameLogColumn, String> line = lines.get(i);
            Game game = new Game();
            //start date
            try {
                game.setStartDate(new Date(dateFormat.parse(line.get(
                        GameLogColumn.DATE)).getTime()));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to parse date for line: " + line);
                }
                continue;
            }
            //game number
            try {
                game.setDayNumber(GameDayNumber.NUMBERS.get(line.get(
                        GameLogColumn.GAME_NUMBER)));
                if (game.getDayNumber() == null) {
                    throw new RetrosheetRuntimeException("No game number!");
                }
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to find the proper game day number for line: " +
                            line, e);
                }
                continue;
            }
            //we don't care about day of week;
            //  we can figure that out on our own if necessary
            //visiting team
            try {
                game.setVisitingTeam(retroContext.getTeams().get(
                        line.get(GameLogColumn.VISITING_TEAM)));
                if (game.getVisitingTeam() == null) {
                    throw new RetrosheetRuntimeException("Visiting team not found");
                }
                //let's check the league too
                if (!game.getVisitingTeam().getLeague().getRetroId().equals(
                        line.get(GameLogColumn.VISITING_LEAGUE))) {
                    throw new RetrosheetRuntimeException("Visiting team from strange league!");
                }
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to get visiting team for line: " + line, e);
                }
                continue;
            }
            //home team
            try {
                game.setHomeTeam(retroContext.getTeams().get(
                        line.get(GameLogColumn.HOME_TEAM)));
                if (game.getHomeTeam() == null) {
                    throw new RetrosheetRuntimeException("Home team not found");
                }
                //let's check the league too
                if (!game.getHomeTeam().getLeague().getRetroId().equals(
                        line.get(GameLogColumn.HOME_LEAGUE))) {
                    throw new RetrosheetRuntimeException("Home team from strange league!");
                }
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to get home team for line: " + line, e);
                }
                continue;
            }
            //visiting team info
            try {
                game.setVisitorInfo(getTeamInfo(line, false));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate visiting team info for line: " + line, e);
                }
                continue;
            }
            //home team info
            try {
                game.setHomeInfo(getTeamInfo(line, true));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate home team info for line: " + line, e);
                }
                continue;
            }
            //total outs
            try {
                game.setOuts(Integer.valueOf(line.get(GameLogColumn.TOTAL_OUTS)));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate outs: " + line, e);
                }
                continue;
            }
            //day/night
            try {
                String dayOrNight = line.get(GameLogColumn.DAY_OR_NIGHT);
                if ("D".equals(dayOrNight)) {
                    game.setDayGame(true);
                } else if ("N".equals(dayOrNight)) {
                    game.setDayGame(false);
                } else {
                    throw new RetrosheetRuntimeException("Unrecognized day/night: " + dayOrNight);
                }
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate day/night: " + line, e);
                }
                continue;
            }
            //completion
            try {
                game.setCompletion(getCompletionInfo(dateFormat, line));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate completion: " + line, e);
                }
                continue;
            }
            //forfeit
            try {
                game.setForfeiture(Forfeiture.FORFEITURES.get(line.get(
                        GameLogColumn.FORFEIT_INFO)));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate forfeiture: " + line, e);
                }
                continue;
            }
            //protest
            try {
                game.setProtests(getProtests(line));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate protests: " + line, e);
                }
                continue;
            }
            //park
            try {
                game.setPark(retroContext.getParks().get(line.get(GameLogColumn.PARK)));
                if (game.getPark() == null) {
                    throw new RetrosheetRuntimeException("Can't find park: " + 
                            line.get(GameLogColumn.PARK));
                }
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate park: " + line, e);
                }
                continue;
            }
            //attendance
            try {
                game.setAttendance(Integer.valueOf(line.get(GameLogColumn.ATTENDANCE)));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate attendance: " + line, e);
                }
                continue;
            }
            //time in minutes
            try {
                game.setTimeInMinutes(Integer.valueOf(line.get(GameLogColumn.TIME_IN_MINUTES)));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate time in minutes: " + line, e);
                }
                continue;
            }
            //additional info
            try {
                game.setHomeTeamBattedFirst("HTBF".equals(line.get(
                        GameLogColumn.ADDITIONAL_INFO)));
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate additional info: " + line, e);
                }
                continue;
            }
            //acquisition information
            try {
                game.setAccuracy(GameAccuracy.ACCURACIES.get(line.get(
                        GameLogColumn.ACQUISITION_INFO)));
                if (game.getAccuracy() == null) {
                    throw new RetrosheetRuntimeException("No accuracy info found");
                }
            } catch (Exception e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unable to populate acquisition info: " + line, e);
                }
                continue;
            }
            //add
            ret.put(GameUtils.generateUniqueId(game), game);
        }
        return ret;
    }

    private List<Protest> getProtests(Map<GameLogColumn, String> line) {
        List<Protest> ret = new ArrayList<Protest>();
        String protests = line.get(GameLogColumn.PROTEST_INFO);
        if (protests != null && !protests.isEmpty()) {
            for (int i = 0; i < protests.length(); i++) {
                ret.add(Protest.valueOf("" + protests.charAt(i)));
            }
        }
        return ret;
    }

    private GameCompletion getCompletionInfo(DateFormat dateFormat,
            Map<GameLogColumn, String> line) throws ParseException {
        GameCompletion ret = new GameCompletion();
        String[] pieces = line.get(GameLogColumn.COMPLETION_INFO).split(",");
        ret.setCompletionDate(new Date(dateFormat.parse(pieces[0]).getTime()));
        ret.setPark(retroContext.getParks().get(pieces[1]));
        ret.setVisitorScoreAtInterruption(Integer.valueOf(pieces[2]));
        ret.setHomeScoreAtInterruption(Integer.valueOf(pieces[3]));
        ret.setOutsAtInterruption(Integer.valueOf(pieces[4]));
        return ret;
    }

    private TeamGameInfo getTeamInfo(Map<GameLogColumn, String> line, 
            boolean homeTeam) {
        TeamGameInfo ret = new TeamGameInfo();
        String prefix = homeTeam ? "HOME" : "VISITING";
        ret.setGameNumber(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_GAME_NUMBER"))));
        ret.setScore(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_SCORE"))));
        ret.setInningScores(GameUtils.parseLineScore(line.get(
                GameLogColumn.valueOf(prefix + "_LINE_SCORE"))));
        ret.setAtBats(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_AT_BATS"))));
        ret.setHits(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_HITS"))));
        ret.setDoubles(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_DOUBLES"))));
        ret.setTriples(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_TRIPLES"))));
        ret.setHomeruns(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_HOMERUNS"))));
        ret.setRbi(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_RBI"))));
        ret.setSacrificeHits(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_SACRIFICE_HITS"))));
        ret.setSacrificeFlies(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_SACRIFICE_FLIES"))));
        ret.setHitByPitch(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_HIT_BY_PITCH"))));
        ret.setWalks(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_WALKS"))));
        ret.setIntentionalWalks(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_INTENTIONAL_WALKS"))));
        ret.setStrikeouts(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_STRIKEOUTS"))));
        ret.setStolenBases(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_STOLEN_BASES"))));
        ret.setCaughtStealing(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_CAUGHT_STEALING"))));
        ret.setGroundedIntoDoublePlays(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_GROUNDED_INTO_DOUBLE_PLAYS"))));
        ret.setOpponentCatcherInterferences(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_FIRST_ON_CATCHER_INTERFERENCE"))));
        ret.setLeftOnBase(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_LEFT_ON_BASE"))));
        ret.setPitchersUsed(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_PITCHERS_USED"))));
        ret.setIndividualEarnedRuns(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_INDIVIDUAL_EARNED_RUNS"))));
        ret.setTeamEarnedRuns(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_TEAM_EARNED_RUNS"))));
        ret.setWildPitches(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_WILD_PITCHES"))));
        ret.setBalks(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_BALKS"))));
        ret.setPutouts(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_PUTOUTS"))));
        ret.setAssists(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_ASSISTS"))));
        ret.setErrors(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_ERRORS"))));
        ret.setPassedBalls(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_PASSED_BALLS"))));
        ret.setDoublePlays(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_DOUBLE_PLAYS"))));
        ret.setTriplePlays(Integer.valueOf(line.get(GameLogColumn.valueOf(
                prefix + "_TRIPLE_PLAYS"))));
        ret.setManager(retroContext.getPersons().get(line.get(
                GameLogColumn.valueOf(prefix + "_MANAGER_ID"))));
        if (ret.getManager() == null) {
            throw new RetrosheetRuntimeException("Manager '" + 
                    line.get(GameLogColumn.valueOf(prefix + "_MANAGER_ID")) +
                    "' not found");
        }
        //build up the game players
        Map<String, GamePlayer> players = new HashMap<String, GamePlayer>(9);
        for (int i = 1; i < 10; i++) {
            GamePlayer player = new GamePlayer();
            player.setPlayer(retroContext.getPersons().get(line.get(
                    GameLogColumn.valueOf(prefix + "_PLAYER_" + i +
                            "_ID"))));
            if (player.getPlayer() == null) {
                throw new RetrosheetRuntimeException("Can't find player: " +
                        line.get(GameLogColumn.valueOf(prefix + 
                                "_PLAYER_" + i + "_ID")));
            }
            player.setPositions(new ArrayList<GamePosition>());
            GamePosition position = new GamePosition();
            position.setBeginningInning(1);
            position.setLineupPosition(i);
            position.setPosition(Position.fromRetroId(Integer.valueOf(line.get(
                    GameLogColumn.valueOf(prefix + "_PLAYER_" + i + "_POSITION")))));
            player.getPositions().add(position);
            players.put(player.getPlayer().getRetroId(), player);
        }
        ret.setGamePlayers(new ArrayList<GamePlayer>(players.values()));
        //starting pitcher (only needed as a game player, rest can be figured out)
        String startingPitcher = line.get(GameLogColumn.valueOf(
                prefix + "_STARTING_PITCHER_ID"));
        GamePlayer starter = players.get(startingPitcher);
        if (starter == null) {
            starter = new GamePlayer();
            starter.setPlayer(retroContext.getPersons().get(startingPitcher));
            starter.setPositions(new ArrayList<GamePosition>());
            GamePosition position = new GamePosition();
            position.setBeginningInning(1);
            position.setPosition(Position.PITCHER);
            starter.getPositions().add(position);
            players.put(startingPitcher, starter);
            ret.getGamePlayers().add(starter);
        }
        //now the winning/losing/saving pitchers (and RBI getters)
        int opponentScore = Integer.valueOf(line.get(GameLogColumn.valueOf(
                (homeTeam ? "VISITING" : "HOME") + "_SCORE")));
        if (opponentScore > ret.getScore()) {
            //losing pitcher
            ret.setWinner(false);
            String losingPitcher = line.get(GameLogColumn.LOSING_PITCHER_ID);
            GamePlayer player = players.get(losingPitcher);
            if (player == null) {
                player = new GamePlayer();
                player.setPlayer(retroContext.getPersons().get(losingPitcher));
                if (player.getPlayer() == null) {
                    throw new RetrosheetRuntimeException("Can't find losing pitcher: " +
                            losingPitcher);
                }
                ret.getGamePlayers().add(player);
            }
            ret.setDecidingPitcher(player);
        } else if (opponentScore < ret.getScore()) {
            //winning pitcher
            ret.setWinner(true);
            String winningPitcher = line.get(GameLogColumn.WINNING_PITCHER_ID);
            GamePlayer player = players.get(winningPitcher);
            if (player == null) {
                player = new GamePlayer();
                player.setPlayer(retroContext.getPersons().get(winningPitcher));
                if (player.getPlayer() == null) {
                    throw new RetrosheetRuntimeException("Can't find winning pitcher: " +
                            winningPitcher);
                }
                players.put(winningPitcher, player);
                ret.getGamePlayers().add(player);
            }
            ret.setDecidingPitcher(player);
            //saving pitcher
            String savingPitcher = line.get(GameLogColumn.SAVING_PITCHER_ID);
            if (savingPitcher != null) {
                if (!savingPitcher.equals(winningPitcher)) {
                    player = players.get(savingPitcher);
                    if (player == null) {
                        player = new GamePlayer();
                        player.setPlayer(retroContext.getPersons().get(savingPitcher));
                        if (player.getPlayer() == null) {
                            throw new RetrosheetRuntimeException("Can't find saving pitcher: " +
                                    winningPitcher);
                        }
                        players.put(savingPitcher, player);
                        ret.getGamePlayers().add(player);
                    }
                }
                ret.setSavingPitcher(player);
            }
            //game winning rbi (don't need to add, can be figured out)
            String gameWinner = line.get(GameLogColumn.GAME_WINNING_RBI_PLAYER_ID);
            if (gameWinner != null) {
                player = players.get(gameWinner);
                if (player == null) {
                    player = new GamePlayer();
                    player.setPlayer(retroContext.getPersons().get(gameWinner));
                    if (player.getPlayer() == null) {
                        throw new RetrosheetRuntimeException("Can't find winning rbi guy: " +
                                winningPitcher);
                    }
                    players.put(gameWinner, player);
                    ret.getGamePlayers().add(player);
                }
            }
        } else {
            throw new RetrosheetRuntimeException("Game ended in tie, " +
                    "can't tell winning/losing pitcher");
        }
        return ret;
    }

}
