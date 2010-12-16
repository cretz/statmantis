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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.statmantis.annotation.XmlInternalProperty;

/**
 * Representation of a {@link Game} completed
 * after suspension/protest
 * 
 * @author Chad Retz
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GameCompletion")
public class GameCompletion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "GameCompletionId", unique = true, nullable = false)
    private long gameCompletionId;

    @XmlInternalProperty("parkId")
    @ManyToOne
    @JoinColumn(name = "ParkId", nullable = false)
    private Park park;
    
    @JoinColumn(name = "VisitorScoreAtInterruption")
    private Integer visitorScoreAtInterruption;
 
    @JoinColumn(name = "HomeScoreAtInterruption")
    private Integer homeScoreAtInterruption;
 
    @JoinColumn(name = "OutsAtInterruption")
    private Integer outsAtInterruption;

    @Temporal(TemporalType.DATE)
    @JoinColumn(name = "CompletionDate", nullable = false)
    private Date completionDate;

    public long getGameCompletionId() {
        return gameCompletionId;
    }

    public void setGameCompletionId(long gameCompletionId) {
        this.gameCompletionId = gameCompletionId;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public Integer getVisitorScoreAtInterruption() {
        return visitorScoreAtInterruption;
    }

    public void setVisitorScoreAtInterruption(Integer visitorScoreAtInterruption) {
        this.visitorScoreAtInterruption = visitorScoreAtInterruption;
    }

    public Integer getHomeScoreAtInterruption() {
        return homeScoreAtInterruption;
    }

    public void setHomeScoreAtInterruption(Integer homeScoreAtInterruption) {
        this.homeScoreAtInterruption = homeScoreAtInterruption;
    }

    public Integer getOutsAtInterruption() {
        return outsAtInterruption;
    }

    public void setOutsAtInterruption(Integer outsAtInterruption) {
        this.outsAtInterruption = outsAtInterruption;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}
