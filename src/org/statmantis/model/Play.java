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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionOfElements;

/**
 * Representation of a play in a {@link Game}
 * 
 * @author Chad Retz
 *
 */
@Entity
@Table(name = "Play")
@SuppressWarnings("serial")
public class Play implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PlayId", unique = true, nullable = false)
    private long playId;
    
    @Column(name = "Inning", nullable = false)
    private int inning;
    
    @Column(name = "Outs", nullable = false)
    private int outs;
    
    @Column(name = "HomeTeam", nullable = false)
    private boolean homeTeam;
    
    @Column(name = "Balls")
    private Integer balls;
    
    @Column(name = "Strikes")
    private Integer strikes;
    
    @Column(name = "Substitution", nullable = false)
    private boolean substitution;
    
    @Column(name = "BattedOutOfTurn", nullable = false)
    private boolean battedOutOfTurn;

    @ManyToOne
    @Column(name = "PlayerId")
    private GamePlayer player;

    @ManyToOne
    @Column(name = "BatterId")
    private GamePlayer batter;
    
    @ManyToOne
    @Column(name = "RunnerAtFirstId")
    private GamePlayer runnerAtFirst;
    
    @ManyToOne
    @Column(name = "RunnerAtSecondId")
    private GamePlayer runnerAtSecond;
    
    @ManyToOne
    @Column(name = "RunnerAtThirdId")
    private GamePlayer runnerAtThird;
    
    @ManyToOne
    @Column(name = "PitcherId")
    private GamePlayer pitcher;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "BatterHandAdjustment", length = HandPreference.MAX_LENGTH)
    private HandPreference batterHandAdjustment;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PitcherHandAdjustment", length = HandPreference.MAX_LENGTH)
    private HandPreference pitcherHandAdjustment;
    
    @Enumerated(EnumType.STRING)
    @CollectionOfElements
    @JoinTable(name = "PlayAtBatEvent",
            joinColumns = @JoinColumn(name = "PlayId"))
    @Column(name = "AtBatEvent")
    private List<AtBatEvent> atBatEvents;

    @OneToOne
    @JoinColumn(name = "PlayEventId")
    private PlayEvent event;
    
    @ManyToMany(mappedBy = "plays")
    private Game game;
    
    @OneToMany
    @JoinColumn(name = "PlayRunnerAdvancementId")
    private List<PlayRunnerAdvancement> advancements;
    
    @OneToMany
    @JoinColumn(name = "PitchId")
    private List<Pitch> pitches;

    @CollectionOfElements
    @JoinTable(name = "PlayComment",
            joinColumns = @JoinColumn(name = "PlayId"))
    @Column(name = "Comment")
    private List<String> comments;

    public long getPlayId() {
        return playId;
    }

    public void setPlayId(long playId) {
        this.playId = playId;
    }

    public int getInning() {
        return inning;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }
    
    public int getOuts() {
        return outs;
    }
    
    public void setOuts(int outs) {
        this.outs = outs;
    }

    public boolean isHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(boolean homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Integer getBalls() {
        return balls;
    }

    public void setBalls(Integer balls) {
        this.balls = balls;
    }

    public Integer getStrikes() {
        return strikes;
    }

    public void setStrikes(Integer strikes) {
        this.strikes = strikes;
    }
    
    public boolean isSubstitution() {
        return substitution;
    }
    
    public void setSubstitution(boolean substitution) {
        this.substitution = substitution;
    }
    
    public boolean isBattedOutOfTurn() {
        return battedOutOfTurn;
    }
    
    public void setBattedOutOfTurn(boolean battedOutOfTurn) {
        this.battedOutOfTurn = battedOutOfTurn;
    }
    
    public GamePlayer getPlayer() {
        return player;
    }
    
    public void setPlayer(GamePlayer player) {
        this.player = player;
    }
    
    public GamePlayer getBatter() {
        return batter;
    }
    
    public void setBatter(GamePlayer batter) {
        this.batter = batter;
    }
    
    public GamePlayer getRunnerAtFirst() {
        return runnerAtFirst;
    }
    
    public void setRunnerAtFirst(GamePlayer runnerAtFirst) {
        this.runnerAtFirst = runnerAtFirst;
    }
    
    public GamePlayer getRunnerAtSecond() {
        return runnerAtSecond;
    }
    
    public void setRunnerAtSecond(GamePlayer runnerAtSecond) {
        this.runnerAtSecond = runnerAtSecond;
    }
    
    public GamePlayer getRunnerAtThird() {
        return runnerAtThird;
    }
    
    public void setRunnerAtThird(GamePlayer runnerAtThird) {
        this.runnerAtThird = runnerAtThird;
    }
    
    public GamePlayer getPitcher() {
        return pitcher;
    }
    
    public void setPitcher(GamePlayer pitcher) {
        this.pitcher = pitcher;
    }
    
    public HandPreference getBatterHandAdjustment() {
        return batterHandAdjustment;
    }
    
    public void setBatterHandAdjustment(HandPreference batterHandAdjustment) {
        this.batterHandAdjustment = batterHandAdjustment;
    }
    
    public HandPreference getPitcherHandAdjustment() {
        return pitcherHandAdjustment;
    }
    
    public void setPitcherHandAdjustment(HandPreference pitcherHandAdjustment) {
        this.pitcherHandAdjustment = pitcherHandAdjustment;
    }

    public List<AtBatEvent> getAtBatEvents() {
        return atBatEvents;
    }

    public void setAtBatEvents(List<AtBatEvent> atBatEvents) {
        this.atBatEvents = atBatEvents;
    }
    
    public PlayEvent getEvent() {
        return event;
    }
    
    public void setEvent(PlayEvent event) {
        this.event = event;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public List<PlayRunnerAdvancement> getAdvancements() {
        return advancements;
    }
    
    public void setAdvancements(List<PlayRunnerAdvancement> advancements) {
        this.advancements = advancements;
    }
    
    public List<Pitch> getPitches() {
        return pitches;
    }
    
    public void setPitches(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
