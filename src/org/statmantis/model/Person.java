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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionOfElements;

/**
 * Representation of a person
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "Person")
@SuppressWarnings("serial")
public class Person implements Serializable, RetrosheetModel, LahmanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LocationId", unique = true, nullable = false)
    private long personId;
    
    @Column(name = "RetroId", unique = true, nullable = false)
    private String retroId;
    
    @Column(name = "LahmanId", unique = true, nullable = false)
    private String lahmanId;
    
    @Column(name = "FirstName", nullable = false)
    private String firstName;
    
    @Column(name = "LastName", nullable = false)
    private String lastName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "BirthDate")
    private Date birthDate;
    
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = 
                @Column(name = "BirthCountry")),
            @AttributeOverride(name = "state", column = 
                @Column(name = "BirthState")),
            @AttributeOverride(name = "city", column = 
                @Column(name = "BirthCity"))
    })
    private Location birth;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DeathDate")
    private Date deathDate;
    
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = 
                @Column(name = "DeathCountry")),
            @AttributeOverride(name = "state", column = 
                @Column(name = "DeathState")),
            @AttributeOverride(name = "city", column = 
                @Column(name = "DeathCity"))
    })
    private Location death;
    
    @Column(name = "Weight")
    private Integer weight;
    
    @Column(name = "Height")
    private Integer height;

    @Enumerated(EnumType.STRING)
    @Column(name = "BattingPreference")
    private HandPreference battingPreference;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ThrowingPreference")
    private HandPreference throwingPreference;

    @Column(name = "College")
    private String college;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Debut")
    private Date debut;

    @CollectionOfElements
    @JoinTable(name = "PersonNickname",
            joinColumns = @JoinColumn(name = "PersonId"))
    @Column(name = "Nickname")
    private List<String> nicknames;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Location getBirth() {
        return birth;
    }

    public void setBirth(Location birth) {
        this.birth = birth;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Location getDeath() {
        return death;
    }

    public void setDeath(Location death) {
        this.death = death;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public HandPreference getBattingPreference() {
        return battingPreference;
    }

    public void setBattingPreference(HandPreference battingPreference) {
        this.battingPreference = battingPreference;
    }

    public HandPreference getThrowingPreference() {
        return throwingPreference;
    }

    public void setThrowingPreference(HandPreference throwingPreference) {
        this.throwingPreference = throwingPreference;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
    
    public Date getDebut() {
        return debut;
    }
    
    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public List<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(List<String> nicknames) {
        this.nicknames = nicknames;
    }
}
