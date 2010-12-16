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
 * Type for a {@link Transaction}
 * 
 * @author Chad Retz
 */
public enum TransactionType implements RetrosheetModel {
    ASSIGN("A", "Assigned from one team to another without compensation"),
    CONDITIONAL("C", "Conditional deal"),
    CONDITIONAL_RETURN("Cr", "Returned to original team after conditional deal"),
    RULE_5_DRAFT("D", "Rule 5 draft pick"),
    AMATEUR_DRAFT("Da", "Amateur draft pick"),
    FIRST_YEAR_DRAFT("Df", "First year draft pick"),
    MINOR_DRAFT("Dm", "Minor league draft pick"),
    AMATEUR_DRAFT_UNSIGNED("Dn", "Amateur draft pick but did not sign"),
    DRAFT_RETURN("Dr", "Returned to original team after draft selection"),
    SPECIAL_DRAFT("Ds", "Special draft pick"),
    AMATEUR_DRAFT_VOID("Dv", "Amateur draft pick voided"),
    FREE_AGENT("F", "Free agent signing"),
    FREE_AGENT_AMATEUR("Fa", "Amateur free agent signing"),
    FREE_AGENT_AMATEUR_BONUS("Fb", "Amateur free agent \"bonus baby\" signing"),
    FREE_AGENT_COMPENSATION("Fc", "Free agent compensation pick"),
    FREE_AGENT_GRANTED("Fg", "Free agent granted"),
    FREE_AGENT_FIRST("Fo", "Free agent signing with first ML team"),
    FREE_AGENT_VOID("Fv", "Free agent signing voided"),
    BEREAVEMENT("Hb", "Went on bereavement list"),
    BEREAVEMENT_RETURN("Hbr", "Came off the bereavement list"),
    INELIGIBLE("Hd", "Declared ineligible"),
    INELIGIBLE_RETURN("Hdr", "Reinstated from ineligible list"),
    DEMOTE("Hf", "Demoted to the minor league"),
    PROMOTE("Hfr", "Promoted to the major league"),
    HELD_OUT("Hh", "Held out"),
    HELD_OUT_RETURN("Hhr", "Ended hold out"),
    DISABLE("Hi", "Went on disabled list"),
    DISABLE_RETURN("Hir", "Came off disabled list"),
    MILITARY("Hm", "Went into military service"),
    MILITARY_RETURN("Hmr", "Returned from military service"),
    SUSPEND("Hs", "Suspended"),
    SUSPEND_RETURN("Hsr", "Reinstated from suspension"),
    UNAVAILABLE("Hu", "Unavailable but not on DL"),
    UNAVAILABLE_RETURN("Hur", "Returned from being unavailable"),
    RETIRE("Hv|Z", "Retired"),
    RETIRE_RETURN("Hvr|Zr", "Unretired"),
    JUMP("J", "Jumped teams"),
    JUMP_RETURN("Jr", "Returned after jumping"),
    LOAN("L", "Loaned"),
    LOAN_RETURN("Lr", "Returned from loan"),
    MINOR_LEAGUE_RIGHTS("M", "Obtained rights when entering minor league agreement"),
    MINOR_LEAGUE_RIGHTS_RETURN("Mr", "Rights returned after agreement ended"),
    PURCHASE("P", "Purchase"),
    PURCHASE_RETURN("Pr", "Returned after purchase"),
    PURCHASE_VOID("Pv", "Purchase voided"),
    RELEASE("R", "Released"),
    TRADE("T", "Traded"),
    TRADE_NO_REPORT("Tn", "Traded but refused to report"),
    TRADE_ADDED("Tp", "Added to trade"),
    TRADE_RETURN("Tr", "Returned after trade"),
    TRADE_VOID("Tv", "Trade voided"),
    UNKNOWN("U", "Unknown (could have been two separate transactions)"),
    LEAGUE_CONTROL("Vg", "Player assigned to league control"),
    LEAGUE_ASSIGN("V", "Player purchased or assigned from league"),
    WAIVER("W", "Waiver pick"),
    WAIVER_FIRST_YEAR("Wf", "First year waiver pick"),
    WAIVER_RETURN("Wr", "Returned after waiver pick"),
    WAIVER_VOID("Wv", "Waiver pick voided"),
    EXPANSION_DRAFT("X", "Expansion draft pick"),
    EXPANSION_DRAFT_ADDED("Xp", "Added as expansion pick at a later date");
    
    public static final int MAX_LENGTH = 26;
    public static final Map<String, TransactionType> TYPES;
    
    static {
        TYPES = EnumUtils.mapEnumerates(TransactionType.class);
    }
    
    private final String retroId;
    private final String friendlyName;
    
    private TransactionType(String retroId, String friendlyName) {
        this.retroId = retroId;
        this.friendlyName = friendlyName;
    }
    
    @Override
    public String getRetroId() {
        return retroId;
    }
    
    public String getFriendlyName() {
        return friendlyName;
    }
    
}
