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
 * Representation of a league for a {@link Team}
 * 
 * @author Chad Retz
 */
@Entity
@Table(name = "League")
@SuppressWarnings("serial")
public class League implements Serializable, RetrosheetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LeagueId", unique = true, nullable = false)
    private long leagueId;
    
    @Column(name = "RetroId", unique = true, nullable = false)
    private String retroId;
    
    @Column(name = "Abbreviation", unique = true, nullable = false)
    private String abbreviation;
    
    @Column(name = "Name", unique = true, nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Level", nullable = false, length = Level.MAX_LENGTH)
    private Level level;

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }
    
    @Override
    public String getRetroId() {
        return retroId;
    }
    
    public void setRetroId(String retroId) {
        this.retroId = retroId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
