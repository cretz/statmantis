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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionOfElements;
import org.statmantis.annotation.XmlInternalProperty;

/**
 * Representation of a team
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "Team")
@SuppressWarnings("serial")
public class Team implements Serializable, RetrosheetModel, LahmanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TeamId", unique = true, nullable = false)
    private long teamId;

    @Column(name = "RetroId", unique = true, nullable = false)
    private String retroId;

    @Column(name = "LahmanId", unique = true, nullable = false)
    private String lahmanId;
    
    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "Name", nullable = false)
    private String name;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Inception", nullable = false)
    private Date inception;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Conclusion")
    private Date conclusion;
    
    @XmlInternalProperty("teamId")
    @OneToOne(mappedBy = "became")
    private Team previous;
    
    @XmlInternalProperty("teamId")
    @OneToOne
    @JoinColumn(name = "BecameId")
    private Team became;
    
    @Column(name = "Comments", length = 400)
    private String comments;
    
    @ManyToOne
    @JoinColumn(name = "LeagueId", nullable = false)
    private League league;
    
    @ManyToMany(mappedBy = "teams")
    private List<Park> parks;

    @ManyToOne
    @JoinColumn(name = "ReferenceId")
    private List<Reference> references;

    @CollectionOfElements
    @JoinTable(name = "TeamOtherName",
            joinColumns = @JoinColumn(name = "TeamId"))
    @Column(name = "OtherName")
    private List<String> otherNames;

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String getRetroId() {
        return retroId;
    }

    public void setRetroId(String retroId) {
        this.retroId = retroId;
    }

    @Override
    public String getLahmanId() {
        return lahmanId;
    }

    public void setLahmanId(String lahmanId) {
        this.lahmanId = lahmanId;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Team getPrevious() {
        return previous;
    }

    public void setPrevious(Team previous) {
        this.previous = previous;
    }

    public Team getBecame() {
        return became;
    }

    public void setBecame(Team became) {
        this.became = became;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Park> getParks() {
        return parks;
    }

    public void setParks(List<Park> parks) {
        this.parks = parks;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }
    
    @Override
    public String toString() {
        return name; 
    }
}
