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

import org.statmantis.annotation.XmlInternalProperty;

/**
 * Representation of a team roster for a year
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "Roster")
@SuppressWarnings("serial")
public class Roster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RosterId", unique = true, nullable = false)
    private long rosterId;
    
    @Column(name = "Year", nullable = false)
    private int year;
    
    @XmlInternalProperty("teamId")
    @ManyToOne
    @JoinColumn(name = "TeamId", nullable = false)
    private Team team;
 
    @XmlInternalProperty("playerId")
    @ManyToMany
    @JoinTable(name = "RosterPlayer",
            joinColumns = @JoinColumn(name = "RosterId"),
            inverseJoinColumns = @JoinColumn(name = "PlayerId"))
    private List<Person> players;

    public long getRosterId() {
        return rosterId;
    }

    public void setRosterId(long rosterId) {
        this.rosterId = rosterId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Person> getPlayers() {
        return players;
    }

    public void setPlayers(List<Person> players) {
        this.players = players;
    }
}
