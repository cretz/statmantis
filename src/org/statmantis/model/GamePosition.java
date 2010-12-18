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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representation of a player's position in a game
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "GamePosition")
@SuppressWarnings("serial")
public class GamePosition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "GamePositionId", unique = true, nullable = false)
    private long gamePositionId;
    
    @Column(name = "BeginningInning", nullable = false)
    private int beginningInning;
    
    @Column(name = "BeginningInningOutCount", nullable = false)
    private int beginningInningOutCount;
    
    @Column(name = "EndingInning")
    private Integer endingInning;
    
    @Column(name = "EndingInningOutCount")
    private Integer endingInningOutCount;

    @Column(name = "LineupPosition")
    private Integer lineupPosition;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Position", length = Position.MAX_LENGTH)
    private Position position;

    public long getGamePositionId() {
        return gamePositionId;
    }

    public void setGamePositionId(long gamePositionId) {
        this.gamePositionId = gamePositionId;
    }

    public int getBeginningInning() {
        return beginningInning;
    }

    public void setBeginningInning(int beginningInning) {
        this.beginningInning = beginningInning;
    }
    
    public int getBeginningInningOutCount() {
        return beginningInningOutCount;
    }
    
    public void setBeginningInningOutCount(int beginningInningOutCount) {
        this.beginningInningOutCount = beginningInningOutCount;
    }
    
    public Integer getEndingInning() {
        return endingInning;
    }
    
    public void setEndingInning(Integer endingInning) {
        this.endingInning = endingInning;
    }
    
    public Integer getEndingInningOutCount() {
        return endingInningOutCount;
    }
    
    public void setEndingInningOutCount(Integer endingInningOutCount) {
        this.endingInningOutCount = endingInningOutCount;
    }

    public Integer getLineupPosition() {
        return lineupPosition;
    }

    public void setLineupPosition(Integer lineupPosition) {
        this.lineupPosition = lineupPosition;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
