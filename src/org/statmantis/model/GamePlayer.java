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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.statmantis.annotation.XmlInternalProperty;

/**
 * Representation of a player in a {@link Game}
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "GamePlayer")
@SuppressWarnings("serial")
public class GamePlayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "EmploymentId", unique = true, nullable = false)
    private long gamePlayerId;
    
    @XmlInternalProperty("personId")
    @ManyToOne
    @JoinColumn(name = "PlayerId", nullable = false)
    private Person player;
    
    @ManyToMany
    @JoinTable(name = "GamePlayerPosition",
            joinColumns = @JoinColumn(name = "GamePlayerId"),
            inverseJoinColumns = @JoinColumn(name = "GamePositionId"))
    @OrderBy("beginningInning")
    private List<GamePosition> positions;
    
    @Column(name = "EarnedRuns")
    private Integer earnedRuns;

    public long getGamePlayerId() {
        return gamePlayerId;
    }

    public void setGamePlayerId(long gamePlayerId) {
        this.gamePlayerId = gamePlayerId;
    }

    public Person getPlayer() {
        return player;
    }

    public void setPlayer(Person player) {
        this.player = player;
    }
    
    public List<GamePosition> getPositions() {
        return positions;
    }
    
    public void setPositions(List<GamePosition> positions) {
        this.positions = positions;
    }

    public Integer getEarnedRuns() {
        return earnedRuns;
    }

    public void setEarnedRuns(Integer earnedRuns) {
        this.earnedRuns = earnedRuns;
    }
}
