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

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Location with latitude and longitude
 * 
 * @author Chad Retz
 */
@Embeddable
@SuppressWarnings("serial")
public class PinPointLocation extends Location {
    
    @Column(name = "Latitude", scale = 6)
    private BigDecimal latitude;
    
    @Column(name = "Longitude", scale = 6)
    private BigDecimal longitude;

    public PinPointLocation() {
        super();
    }

    public PinPointLocation(String country, String city, StateProvince state,
            BigDecimal latitude, BigDecimal longitude) {
        super(country, city, state);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
