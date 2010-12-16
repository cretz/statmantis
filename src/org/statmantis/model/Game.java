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
import java.sql.Date;
import java.sql.Time;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionOfElements;
import org.statmantis.annotation.XmlInternalProperty;
import org.statmantis.annotation.XmlTopLevel;

/**
 * Representation of a baseball game
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "Game")
@XmlTopLevel
@SuppressWarnings("serial")
public class Game implements Serializable, RetrosheetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "GameId", unique = true, nullable = false)
    private long gameId;

    @Column(name = "RetroId", unique = true, nullable = false)
    private String retroId;

    @XmlInternalProperty("parkId")
    @ManyToOne
    @JoinColumn(name = "ParkId", nullable = false)
    private Park park;

    @XmlInternalProperty("teamId")
    @ManyToOne
    @JoinColumn(name = "VisitingTeamId", nullable = false)
    private Team visitingTeam;

    @ManyToOne
    @JoinColumn(name = "VistorInfoId", nullable = false)
    private TeamGameInfo visitorInfo;

    @XmlInternalProperty("teamId")
    @ManyToOne
    @JoinColumn(name = "HomeTeamId", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "HomeInfoId", nullable = false)
    private TeamGameInfo homeInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "DayNumber", length = GameDayNumber.MAX_LENGTH)
    private GameDayNumber dayNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "StartDate", nullable = false)
    private Date startDate;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "StartTime")
    private Time startTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "ScheduledStartDate")
    private Date scheduledStartDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", length = GameType.MAX_LENGTH)
    private GameType type;

    @Column(name = "Outs", nullable = false)
    private int outs;
    
    @Column(name = "DayGame", nullable = false)
    private boolean dayGame;
    
    @ManyToOne
    @JoinColumn(name = "CompletionId")
    private GameCompletion completion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Forfeiture", length = Forfeiture.MAX_LENGTH)
    private Forfeiture forfeiture;
    
    @Column(name = "Attendance")
    private Integer attendance;
    
    @Column(name = "TimeInMinutes")
    private Integer timeInMinutes;
    
    @ManyToOne
    @JoinColumn(name = "UmpireHomeId")
    private Person umpireHome;
    
    @ManyToOne
    @JoinColumn(name = "UmpireFirstId")
    private Person umpireFirst;
    
    @ManyToOne
    @JoinColumn(name = "UmpireSecondId")
    private Person umpireSecond;
    
    @ManyToOne
    @JoinColumn(name = "UmpireThirdId")
    private Person umpireThird;
    
    @ManyToOne
    @JoinColumn(name = "UmpireLeftId")
    private Person umpireLeft;
    
    @ManyToOne
    @JoinColumn(name = "UmpireRightId")
    private Person umpireRight;
    
    @Column(name = "HomeTeamBattedFirst", nullable = false)
    private boolean homeTeamBattedFirst;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Forfeiture", length = Forfeiture.MAX_LENGTH)
    private GameAccuracy accuracy;
    
    @Column(name = "DesignatedHitterPresent", nullable = false)
    private boolean designatedHitterPresent;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "FieldCondition", length = FieldCondition.MAX_LENGTH)
    private FieldCondition fieldCondition;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Precipitation", length = Precipitation.MAX_LENGTH)
    private Precipitation precipitation;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "SkyCondition", length = SkyCondition.MAX_LENGTH)
    private SkyCondition skyCondition;
    
    @Column(name = "Temperature")
    private Integer temperature;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "WindDirection", length = WindDirection.MAX_LENGTH)
    private WindDirection windDirection;
    
    @Column(name = "Temperature")
    private Integer windspeed;
    
    @ManyToMany
    @JoinTable(name = "GamePlay",
            joinColumns = @JoinColumn(name = "GameId"),
            inverseJoinColumns = @JoinColumn(name = "PlayId"))
    private List<Play> plays;
    
    @Enumerated(EnumType.STRING)
    @CollectionOfElements
    @JoinTable(name = "GameProtest",
            joinColumns = @JoinColumn(name = "GameId"))
    @Column(name = "Protest", length = Protest.MAX_LENGTH)
    private List<Protest> protests;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    @Override
    public String getRetroId() {
        return retroId;
    }

    public void setRetroId(String retroId) {
        this.retroId = retroId;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public Team getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(Team visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public TeamGameInfo getVisitorInfo() {
        return visitorInfo;
    }

    public void setVisitorInfo(TeamGameInfo visitorInfo) {
        this.visitorInfo = visitorInfo;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamGameInfo getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(TeamGameInfo homeInfo) {
        this.homeInfo = homeInfo;
    }

    public GameDayNumber getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(GameDayNumber dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Time getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getScheduledStartDate() {
        return scheduledStartDate;
    }

    public void setScheduledStartDate(Date scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public int getOuts() {
        return outs;
    }

    public void setOuts(int outs) {
        this.outs = outs;
    }

    public boolean isDayGame() {
        return dayGame;
    }

    public void setDayGame(boolean dayGame) {
        this.dayGame = dayGame;
    }

    public GameCompletion getCompletion() {
        return completion;
    }

    public void setCompletion(GameCompletion completion) {
        this.completion = completion;
    }

    public Forfeiture getForfeiture() {
        return forfeiture;
    }

    public void setForfeiture(Forfeiture forfeiture) {
        this.forfeiture = forfeiture;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Integer getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Integer timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public Person getUmpireHome() {
        return umpireHome;
    }

    public void setUmpireHome(Person umpireHome) {
        this.umpireHome = umpireHome;
    }

    public Person getUmpireFirst() {
        return umpireFirst;
    }

    public void setUmpireFirst(Person umpireFirst) {
        this.umpireFirst = umpireFirst;
    }

    public Person getUmpireSecond() {
        return umpireSecond;
    }

    public void setUmpireSecond(Person umpireSecond) {
        this.umpireSecond = umpireSecond;
    }

    public Person getUmpireThird() {
        return umpireThird;
    }

    public void setUmpireThird(Person umpireThird) {
        this.umpireThird = umpireThird;
    }

    public Person getUmpireLeft() {
        return umpireLeft;
    }

    public void setUmpireLeft(Person umpireLeft) {
        this.umpireLeft = umpireLeft;
    }

    public Person getUmpireRight() {
        return umpireRight;
    }

    public void setUmpireRight(Person umpireRight) {
        this.umpireRight = umpireRight;
    }

    public boolean isHomeTeamBattedFirst() {
        return homeTeamBattedFirst;
    }

    public void setHomeTeamBattedFirst(boolean homeTeamBattedFirst) {
        this.homeTeamBattedFirst = homeTeamBattedFirst;
    }

    public GameAccuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(GameAccuracy accuracy) {
        this.accuracy = accuracy;
    }

    public boolean isDesignatedHitterPresent() {
        return designatedHitterPresent;
    }

    public void setDesignatedHitterPresent(boolean designatedHitterPresent) {
        this.designatedHitterPresent = designatedHitterPresent;
    }

    public FieldCondition getFieldCondition() {
        return fieldCondition;
    }

    public void setFieldCondition(FieldCondition fieldCondition) {
        this.fieldCondition = fieldCondition;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public SkyCondition getSkyCondition() {
        return skyCondition;
    }

    public void setSkyCondition(SkyCondition skyCondition) {
        this.skyCondition = skyCondition;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }

    public Integer getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(Integer windspeed) {
        this.windspeed = windspeed;
    }
    
    public List<Play> getPlays() {
        return plays;
    }
    
    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }

    public List<Protest> getProtests() {
        return protests;
    }

    public void setProtests(List<Protest> protests) {
        this.protests = protests;
    }
    
    @Override
    public String toString() {
        return retroId;
    }
}
