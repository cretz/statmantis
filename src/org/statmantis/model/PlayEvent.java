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

/**
 * Representation of an event in a {@link Play}
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "PlayEvent")
@SuppressWarnings("serial")
public class PlayEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PlayEventId", unique = true, nullable = false)
    private long playEventId;
    
    @ManyToOne
    @Column(name = "PrimaryBaseRunnerId")
    private GamePlayer primaryBaseRunner;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PrimaryRunningFrom")
    private Base primaryRunningFrom;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PrimaryRunningTo")
    private Base primaryRunningTo;
    
    @ManyToOne
    @Column(name = "SecondaryBaseRunnerId")
    private GamePlayer secondaryBaseRunner;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "SecondaryRunningFrom")
    private Base secondaryRunningFrom;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "SecondaryRunningTo")
    private Base secondaryRunningTo;
    
    @ManyToOne
    @Column(name = "TertiaryBaseRunnerId")
    private GamePlayer tertiaryBaseRunner;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TeriaryRunningFrom")
    private Base tertiaryRunningFrom;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TertiaryRunningTo")
    private Base tertiaryRunningTo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ErrorType")
    private ErrorType errorType;
    
    @Column(name = "Out", nullable = false)
    private boolean out;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PlayType", nullable = false, length = PlayType.MAX_LENGTH)
    private PlayType playType;
    
    @OneToOne
    @JoinColumn(name = "SecondaryEventId")
    private PlayEvent secondaryEvent;
    
    @ManyToMany
    @JoinTable(name = "PlayEventFielder",
            joinColumns = @JoinColumn(name = "PlayEventId"),
            inverseJoinColumns = @JoinColumn(name = "FielderId"))
    private List<GamePlayer> fielders;

    @OneToMany
    @JoinColumn(name = "PlayEventId")
    private List<PlayModifier> modifiers;

    public long getPlayEventId() {
        return playEventId;
    }

    public void setPlayEventId(long playEventId) {
        this.playEventId = playEventId;
    }

    public GamePlayer getPrimaryBaseRunner() {
        return primaryBaseRunner;
    }
    
    public void setPrimaryBaseRunner(GamePlayer primaryBaseRunner) {
        this.primaryBaseRunner = primaryBaseRunner;
    }
    
    public Base getPrimaryRunningFrom() {
        return primaryRunningFrom;
    }
    
    public void setPrimaryRunningFrom(Base primaryRunningFrom) {
        this.primaryRunningFrom = primaryRunningFrom;
    }
    
    public Base getPrimaryRunningTo() {
        return primaryRunningTo;
    }
    
    public void setPrimaryRunningTo(Base primaryRunningTo) {
        this.primaryRunningTo = primaryRunningTo;
    }
    
    public GamePlayer getSecondaryBaseRunner() {
        return secondaryBaseRunner;
    }
    
    public void setSecondaryBaseRunner(GamePlayer secondaryBaseRunner) {
        this.secondaryBaseRunner = secondaryBaseRunner;
    }
    
    public Base getSecondaryRunningFrom() {
        return secondaryRunningFrom;
    }
    
    public void setSecondaryRunningFrom(Base secondaryRunningFrom) {
        this.secondaryRunningFrom = secondaryRunningFrom;
    }
    
    public Base getSecondaryRunningTo() {
        return secondaryRunningTo;
    }
    
    public void setSecondaryRunningTo(Base secondaryRunningTo) {
        this.secondaryRunningTo = secondaryRunningTo;
    }
    
    public GamePlayer getTertiaryBaseRunner() {
        return tertiaryBaseRunner;
    }
    
    public void setTertiaryBaseRunner(GamePlayer tertiaryBaseRunner) {
        this.tertiaryBaseRunner = tertiaryBaseRunner;
    }
    
    public Base getTertiaryRunningFrom() {
        return tertiaryRunningFrom;
    }
    
    public void setTertiaryRunningFrom(Base tertiaryRunningFrom) {
        this.tertiaryRunningFrom = tertiaryRunningFrom;
    }
    
    public Base getTertiaryRunningTo() {
        return tertiaryRunningTo;
    }
    
    public void setTertiaryRunningTo(Base tertiaryRunningTo) {
        this.tertiaryRunningTo = tertiaryRunningTo;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
    
    public PlayType getPlayType() {
        return playType;
    }
    
    public void setPlayType(PlayType playType) {
        this.playType = playType;
    }
    
    public PlayEvent getSecondaryEvent() {
        return secondaryEvent;
    }
    
    public void setSecondaryEvent(PlayEvent secondaryEvent) {
        this.secondaryEvent = secondaryEvent;
    }

    public List<GamePlayer> getFielders() {
        return fielders;
    }

    public void setFielders(List<GamePlayer> fielders) {
        this.fielders = fielders;
    }
    
    public List<PlayModifier> getModifiers() {
        return modifiers;
    }
    
    public void setModifiers(List<PlayModifier> modifiers) {
        this.modifiers = modifiers;
    }
}
