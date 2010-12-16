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
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * A location
 * 
 * @author Chad Retz
 */
@Embeddable
@SuppressWarnings("serial")
public class Location implements Serializable {
    
    @Column(name = "Country")
    private String country;

    @Column(name = "City")
    private String city;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "State", length = 2)
    private StateProvince state;
    
    public Location() {
    }

    public Location(String country, String city, StateProvince state) {
        this.country = country;
        this.city = city;
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public StateProvince getState() {
        return state;
    }

    public void setState(StateProvince state) {
        this.state = state;
    }
}
