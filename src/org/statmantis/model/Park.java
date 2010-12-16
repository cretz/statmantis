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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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



/**
 * Representation of a ballpark
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "Park")
@SuppressWarnings("serial")
public class Park implements Serializable, RetrosheetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LocationId", unique = true, nullable = false)
    private long parkId;
    
    @Column(name = "RetroId", unique = true, nullable = false)
    private String retroId;
    
    @Column(name = "Name", nullable = false)
    private String name;
    
    @Embedded
    private PinPointLocation location;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Inception", nullable = false)
    private Date inception;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Conclusion", nullable = false)
    private Date conclusion;

    @Column(name = "Comments", nullable = false)
    private String comments;
    
    @ManyToOne
    @JoinColumn(name = "ReferenceId")
    private List<Reference> references;
    
    @XmlInternalProperty("teamId")
    @ManyToMany
    @JoinTable(name = "ParkTeam",
            joinColumns = @JoinColumn(name = "ParkId"),
            inverseJoinColumns = @JoinColumn(name = "TeamId"))
    private List<Team> teams;

    @CollectionOfElements
    @JoinTable(name = "ParkPreviousName",
            joinColumns = @JoinColumn(name = "ParkId"))
    @Column(name = "PreviousName")
    private List<String> previousNames;

    public long getParkId() {
        return parkId;
    }

    public void setParkId(long parkId) {
        this.parkId = parkId;
    }

    @Override
    public String getRetroId() {
        return retroId;
    }

    public void setRetroId(String retroId) {
        this.retroId = retroId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(PinPointLocation location) {
        this.location = location;
    }

    public Date getInception() {
        return inception;
    }

    public void setInception(Date inception) {
        this.inception = inception;
    }

    public Date getConclusion() {
        return conclusion;
    }

    public void setConclusion(Date conclusion) {
        this.conclusion = conclusion;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<String> getPreviousNames() {
        return previousNames;
    }

    public void setPreviousNames(List<String> previousNames) {
        this.previousNames = previousNames;
    }
}
