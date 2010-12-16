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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Modifier for a play event
 *
 * @author Chad Retz
 */
@Entity
@Table(name = "PlayModifier")
@SuppressWarnings("serial")
public class PlayModifier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PlayModifierId", unique = true, nullable = false)
    private long playModifierId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Type", length = PlayModifierType.MAX_LENGTH)
    private PlayModifierType type;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Type", length = FieldLocation.MAX_LENGTH)
    private FieldLocation location;

    @ManyToOne
    @Column(name = "FielderId")
    private GamePlayer fielder;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Base")
    private Base base;

    public long getPlayModifierId() {
        return playModifierId;
    }

    public void setPlayModifierId(long playModifierId) {
        this.playModifierId = playModifierId;
    }

    public PlayModifierType getType() {
        return type;
    }

    public void setType(PlayModifierType type) {
        this.type = type;
    }
    
    public FieldLocation getLocation() {
        return location;
    }
    
    public void setLocation(FieldLocation location) {
        this.location = location;
    }

    public GamePlayer getFielder() {
        return fielder;
    }

    public void setFielder(GamePlayer fielder) {
        this.fielder = fielder;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }
}
