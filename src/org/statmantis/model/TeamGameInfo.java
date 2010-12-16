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
package org.statmantis.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionOfElements;
import org.statmantis.annotation.XmlInternalProperty;

/**
 * Info for a team in a {@link Game}
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "TeamGameInfo")
@SuppressWarnings("serial")
public class TeamGameInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TeamGameInfoId", unique = true, nullable = false)
    private long teamGameInfoId;

    @Column(name = "GameNumber", nullable = false)
    private int gameNumber;
    
    @Column(name = "Score", nullable = false)
    private int score;

    @CollectionOfElements
    @JoinTable(name = "TeamGameInfoLineScore",
            joinColumns = @JoinColumn(name = "TeamId"))
    @Column(name = "InningScore", nullable = false)
    private List<Integer> inningScores;
    
    @Column(name = "AtBats", nullable = false)
    private int atBats;
    
    @Column(name = "Hits", nullable = false)
    private int hits;
    
    @Column(name = "Doubles", nullable = false)
    private int doubles;
    
    @Column(name = "Triples", nullable = false)
    private int triples;
    
    @Column(name = "Homeruns", nullable = false)
    private int homeruns;
    
    @Column(name = "Rbi", nullable = false)
    private int rbi;
    
    @Column(name = "SacrificeHits", nullable = false)
    private int sacrificeHits;

    @Column(name = "SacrificeFlies", nullable = false)
    private int sacrificeFlies;

    @Column(name = "HitByPitch", nullable = false)
    private int hitByPitch;

    @Column(name = "Walks", nullable = false)
    private int walks;

    @Column(name = "IntentionalWalks", nullable = false)
    private int intentionalWalks;

    @Column(name = "Strikeouts", nullable = false)
    private int strikeouts;

    @Column(name = "StolenBases", nullable = false)
    private int stolenBases;

    @Column(name = "CaughtStealing", nullable = false)
    private int caughtStealing;

    @Column(name = "GroundedIntoDoublePlays", nullable = false)
    private int groundedIntoDoublePlays;

    @Column(name = "OpponentCatcherInterferences", nullable = false)
    private int opponentCatcherInterferences;

    @Column(name = "LeftOnBase", nullable = false)
    private int leftOnBase;

    @Column(name = "PitchersUsed", nullable = false)
    private int pitchersUsed;

    @Column(name = "IndividualEarnedRuns", nullable = false)
    private int individualEarnedRuns;

    @Column(name = "TeamEarnedRuns", nullable = false)
    private int teamEarnedRuns;

    @Column(name = "WildPitches", nullable = false)
    private int wildPitches;

    @Column(name = "Balks", nullable = false)
    private int balks;

    @Column(name = "Putouts", nullable = false)
    private int putouts;

    @Column(name = "Assists", nullable = false)
    private int assists;

    @Column(name = "Errors", nullable = false)
    private int errors;

    @Column(name = "PassedBalls", nullable = false)
    private int passedBalls;

    @Column(name = "DoublePlays", nullable = false)
    private int doublePlays;

    @Column(name = "TriplePlays", nullable = false)
    private int triplePlays;

    @XmlInternalProperty("personId")
    @ManyToOne
    @JoinColumn(name = "ManagerId", nullable = false)
    private Person manager;
    
    @XmlInternalProperty("gamePlayerId")
    @ManyToOne
    @JoinColumn(name = "DecidingPitcherId", nullable = false)
    private GamePlayer decidingPitcher;

    @Column(name = "Winner", nullable = false)
    private boolean winner;
    
    @XmlInternalProperty("gamePlayerId")
    @ManyToOne
    @JoinColumn(name = "SavingPitcherId")
    private GamePlayer savingPitcher;

    @XmlInternalProperty("gamePlayerId")
    @ManyToOne
    @JoinColumn(name = "WinningBatterId")
    private GamePlayer winningBatter;
    
    @ManyToMany
    @JoinTable(name = "TeamGamePlayer",
            joinColumns = @JoinColumn(name = "TeamGameInfoId"),
            inverseJoinColumns = @JoinColumn(name = "GamePlayerId"))
    private List<GamePlayer> gamePlayers;

    public long getTeamGameInfoId() {
        return teamGameInfoId;
    }

    public void setTeamGameInfoId(long teamGameInfoId) {
        this.teamGameInfoId = teamGameInfoId;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Integer> getInningScores() {
        return inningScores;
    }

    public void setInningScores(List<Integer> inningScores) {
        this.inningScores = inningScores;
    }

    public int getAtBats() {
        return atBats;
    }

    public void setAtBats(int atBats) {
        this.atBats = atBats;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getDoubles() {
        return doubles;
    }

    public void setDoubles(int doubles) {
        this.doubles = doubles;
    }

    public int getTriples() {
        return triples;
    }

    public void setTriples(int triples) {
        this.triples = triples;
    }

    public int getHomeruns() {
        return homeruns;
    }

    public void setHomeruns(int homeruns) {
        this.homeruns = homeruns;
    }

    public int getRbi() {
        return rbi;
    }

    public void setRbi(int rbi) {
        this.rbi = rbi;
    }

    public int getSacrificeHits() {
        return sacrificeHits;
    }

    public void setSacrificeHits(int sacrificeHits) {
        this.sacrificeHits = sacrificeHits;
    }

    public int getSacrificeFlies() {
        return sacrificeFlies;
    }

    public void setSacrificeFlies(int sacrificeFlies) {
        this.sacrificeFlies = sacrificeFlies;
    }

    public int getHitByPitch() {
        return hitByPitch;
    }

    public void setHitByPitch(int hitByPitch) {
        this.hitByPitch = hitByPitch;
    }

    public int getWalks() {
        return walks;
    }

    public void setWalks(int walks) {
        this.walks = walks;
    }

    public int getIntentionalWalks() {
        return intentionalWalks;
    }

    public void setIntentionalWalks(int intentionalWalks) {
        this.intentionalWalks = intentionalWalks;
    }

    public int getStrikeouts() {
        return strikeouts;
    }

    public void setStrikeouts(int strikeouts) {
        this.strikeouts = strikeouts;
    }

    public int getStolenBases() {
        return stolenBases;
    }

    public void setStolenBases(int stolenBases) {
        this.stolenBases = stolenBases;
    }

    public int getCaughtStealing() {
        return caughtStealing;
    }

    public void setCaughtStealing(int caughtStealing) {
        this.caughtStealing = caughtStealing;
    }

    public int getGroundedIntoDoublePlays() {
        return groundedIntoDoublePlays;
    }

    public void setGroundedIntoDoublePlays(int groundedIntoDoublePlays) {
        this.groundedIntoDoublePlays = groundedIntoDoublePlays;
    }

    public int getOpponentCatcherInterferences() {
        return opponentCatcherInterferences;
    }

    public void setOpponentCatcherInterferences(int opponentCatcherInterferences) {
        this.opponentCatcherInterferences = opponentCatcherInterferences;
    }

    public int getLeftOnBase() {
        return leftOnBase;
    }

    public void setLeftOnBase(int leftOnBase) {
        this.leftOnBase = leftOnBase;
    }

    public int getPitchersUsed() {
        return pitchersUsed;
    }

    public void setPitchersUsed(int pitchersUsed) {
        this.pitchersUsed = pitchersUsed;
    }

    public int getIndividualEarnedRuns() {
        return individualEarnedRuns;
    }

    public void setIndividualEarnedRuns(int individualEarnedRuns) {
        this.individualEarnedRuns = individualEarnedRuns;
    }

    public int getTeamEarnedRuns() {
        return teamEarnedRuns;
    }

    public void setTeamEarnedRuns(int teamEarnedRuns) {
        this.teamEarnedRuns = teamEarnedRuns;
    }

    public int getWildPitches() {
        return wildPitches;
    }

    public void setWildPitches(int wildPitches) {
        this.wildPitches = wildPitches;
    }

    public int getBalks() {
        return balks;
    }

    public void setBalks(int balks) {
        this.balks = balks;
    }

    public int getPutouts() {
        return putouts;
    }

    public void setPutouts(int putouts) {
        this.putouts = putouts;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public int getPassedBalls() {
        return passedBalls;
    }

    public void setPassedBalls(int passedBalls) {
        this.passedBalls = passedBalls;
    }

    public int getDoublePlays() {
        return doublePlays;
    }

    public void setDoublePlays(int doublePlays) {
        this.doublePlays = doublePlays;
    }

    public int getTriplePlays() {
        return triplePlays;
    }

    public void setTriplePlays(int triplePlays) {
        this.triplePlays = triplePlays;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public GamePlayer getDecidingPitcher() {
        return decidingPitcher;
    }

    public void setDecidingPitcher(GamePlayer decidingPitcher) {
        this.decidingPitcher = decidingPitcher;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public GamePlayer getSavingPitcher() {
        return savingPitcher;
    }

    public void setSavingPitcher(GamePlayer savingPitcher) {
        this.savingPitcher = savingPitcher;
    }

    public GamePlayer getWinningBatter() {
        return winningBatter;
    }

    public void setWinningBatter(GamePlayer winningBatter) {
        this.winningBatter = winningBatter;
    }
    
    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
    
    public void setGamePlayers(List<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
}
