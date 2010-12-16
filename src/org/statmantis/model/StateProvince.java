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

import java.util.Map;

import org.statmantis.util.EnumUtils;

/**
 * States/provinces
 * 
 * @author Chad Retz
 */
public enum StateProvince implements RetrosheetModel {
    AL("ALABAMA"),
    AK("ALASKA"),
    AS("AMERICAN SAMOA"),
    AZ("ARIZONA"),
    AR("ARKANSAS"),
    CA("CALIFORNIA"),
    CO("COLORADO"),
    CT("CONNECTICUT"),
    DE("DELAWARE"),
    DC("DISTRICT OF COLUMBIA"),
    FM("FEDERATED STATES OF MICRONESIA"),
    FL("FLORIDA"),
    GA("GEORGIA"),
    GU("GUAM"),
    HI("HAWAII"),
    ID("IDAHO"),
    IL("ILLINOIS"),
    IN("INDIANA"),
    IA("IOWA"),
    KS("KANSAS"),
    KY("KENTUCKY"),
    LA("LOUISIANA"),
    ME("MAINE"),
    MH("MARSHALL ISLANDS"),
    MD("MARYLAND"),
    MA("MASSACHUSETTS"),
    MI("MICHIGAN"),
    MN("MINNESOTA"),
    MS("MISSISSIPPI"),
    MO("MISSOURI"),
    MT("MONTANA"),
    NE("NEBRASKA"),
    NV("NEVADA"),
    NH("NEW HAMPSHIRE"),
    NJ("NEW JERSEY"),
    NM("NEW MEXICO"),
    NY("NEW YORK"),
    NC("NORTH CAROLINA"),
    ND("NORTH DAKOTA"),
    MP("NORTHERN MARIANA ISLANDS"),
    OH("OHIO"),
    OK("OKLAHOMA"),
    OR("OREGON"),
    PW("PALAU"),
    PA("PENNSYLVANIA"),
    PR("PUERTO RICO"),
    RI("RHODE ISLAND"),
    SC("SOUTH CAROLINA"),
    SD("SOUTH DAKOTA"),
    TN("TENNESSEE"),
    TX("TEXAS"),
    UT("UTAH"),
    VT("VERMONT"),
    VI("VIRGIN ISLANDS"),
    VA("VIRGINIA"),
    WA("WASHINGTON"),
    WV("WEST VIRGINIA"),
    WI("WISCONSIN"),
    WY("WYOMING"),
    AB("Alberta"),
    BC("British Columbia"),
    MB("Manitoba"),
    NB("New Brunswick"),
    NL("Newfoundland and Labrador"),
    NT("Northwest Territories"),
    NS("Nova Scotia"),
    NU("Nunavut"),
    ON("Ontario", "ONT"),
    PE("Prince Edward Island"),
    QC("Quebec", "QUE"),
    SK("Saskatchewan"),
    YT("Yukon Yukon");
    
    /**
     * Keyed by retroId
     */
    public static final Map<String, StateProvince> STATES;
    
    static {
        STATES = EnumUtils.mapEnumerates(StateProvince.class);
    }
    
    private final String friendlyName;
    private final String retroId;
    
    private StateProvince(String friendlyName) {
        this(friendlyName, null);
    }
    
    private StateProvince(String friendlyName, String retroId) {
        this.friendlyName = friendlyName;
        this.retroId = retroId == null ? name() : retroId;
    }
    
    public String getFriendlyName() {
        return friendlyName;
    }
    
    @Override
    public String getRetroId() {
        return retroId;
    }
}
