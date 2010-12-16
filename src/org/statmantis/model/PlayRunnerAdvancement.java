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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Runner advancement on a play
 *
 * @author Chad Retz
 */
@Entity
@Table(name = "PlayEvent")
@SuppressWarnings("serial")
public class PlayRunnerAdvancement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PlayRunnerAdvancementId", unique = true, nullable = false)
    private long playRunnerAdvancementId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "BaseFrom", length = Base.MAX_LENGTH)
    private Base baseFrom;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "BaseTo", length = Base.MAX_LENGTH)
    private Base baseTo;
    
    @Column(name = "Out", nullable = false)
    private boolean out;

    @OneToMany
    @JoinColumn(name = "PlayModifierId")
    private List<PlayModifier> modifiers;
    
    @ManyToMany
    @JoinTable(name = "PlayRunnerAdvancementFielder",
            joinColumns = @JoinColumn(name = "PlayRunnerAdvancementId"),
            inverseJoinColumns = @JoinColumn(name = "FielderId"))
    private List<GamePlayer> fielders;

    public long getPlayRunnerAdvancementId() {
        return playRunnerAdvancementId;
    }

    public void setPlayRunnerAdvancementId(long playRunnerAdvancementId) {
        this.playRunnerAdvancementId = playRunnerAdvancementId;
    }

    public Base getBaseFrom() {
        return baseFrom;
    }

    public void setBaseFrom(Base baseFrom) {
        this.baseFrom = baseFrom;
    }

    public Base getBaseTo() {
        return baseTo;
    }

    public void setBaseTo(Base baseTo) {
        this.baseTo = baseTo;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public List<PlayModifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<PlayModifier> modifiers) {
        this.modifiers = modifiers;
    }
    
    public List<GamePlayer> getFielders() {
        return fielders;
    }
    
    public void setFielders(List<GamePlayer> fielders) {
        this.fielders = fielders;
    }
}
