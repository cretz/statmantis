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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.statmantis.annotation.XmlInternalProperty;

/**
 * Representation of a basaeball transaction
 * 
 * @author Chad Retz
 */
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TransactionId", unique = true, nullable = false)
    private long transactionId;
    
    @Column(name = "RetroId", unique = true, nullable = false)
    private String retroId;
    
    @Embedded
    private ApproximateDate date;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Time", length = TransactionTime.MAX_LENGTH)
    private TransactionTime time;
    
    @Column(name = "Approximate", nullable = false)
    private boolean approximate;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "year", column = 
            @Column(name = "SecondaryYear", length = 4)),
        @AttributeOverride(name = "month", column = 
            @Column(name = "SecondaryMonth", length = 2)),
        @AttributeOverride(name = "day", column = 
            @Column(name = "SecondaryDay", length = 2))
    })
    private ApproximateDate secondaryDate;
    
    @XmlInternalProperty("personId")
    @ManyToOne
    @JoinColumn(name = "PersonId")
    private Person person;
    
    @Column(name = "UnknownFirstName", length = 100)
    private String unknownFirstName;
    
    @Column(name = "UnknownLastName", length = 100)
    private String unknownLastName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false, length = TransactionType.MAX_LENGTH)
    private TransactionType type;
    
    @XmlInternalProperty("teamId")
    @ManyToOne
    @JoinColumn(name = "FromTeamId")
    private Team fromTeam;
    
    @ManyToOne
    @JoinColumn(name = "FromLeagueId")
    private League fromLeague;
    
    @XmlInternalProperty("teamId")
    @ManyToOne
    @JoinColumn(name = "ToTeamId")
    private Team toTeam;
    
    @ManyToOne
    @JoinColumn(name = "ToLeagueId")
    private League toLeague;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "DraftType", length = DraftType.MAX_LENGTH)
    private DraftType draftType;
    
    @Column(name = "DraftRound")
    private Integer draftRound;
    
    @Column(name = "Amount")
    private Float amount;
    
    @Column(name = "Info", length = 400)
    private String info;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getRetroId() {
        return retroId;
    }

    public void setRetroId(String retroId) {
        this.retroId = retroId;
    }

    public ApproximateDate getDate() {
        return date;
    }

    public void setDate(ApproximateDate date) {
        this.date = date;
    }

    public TransactionTime getTime() {
        return time;
    }

    public void setTime(TransactionTime time) {
        this.time = time;
    }

    public boolean isApproximate() {
        return approximate;
    }

    public void setApproximate(boolean approximate) {
        this.approximate = approximate;
    }

    public ApproximateDate getSecondaryDate() {
        return secondaryDate;
    }

    public void setSecondaryDate(ApproximateDate secondaryDate) {
        this.secondaryDate = secondaryDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUnknownFirstName() {
        return unknownFirstName;
    }

    public void setUnknownFirstName(String unknownFirstName) {
        this.unknownFirstName = unknownFirstName;
    }

    public String getUnknownLastName() {
        return unknownLastName;
    }

    public void setUnknownLastName(String unknownLastName) {
        this.unknownLastName = unknownLastName;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public void setType(TransactionType type) {
        this.type = type;
    }

    public Team getFromTeam() {
        return fromTeam;
    }

    public void setFromTeam(Team fromTeam) {
        this.fromTeam = fromTeam;
    }

    public League getFromLeague() {
        return fromLeague;
    }

    public void setFromLeague(League fromLeague) {
        this.fromLeague = fromLeague;
    }

    public Team getToTeam() {
        return toTeam;
    }

    public void setToTeam(Team toTeam) {
        this.toTeam = toTeam;
    }

    public League getToLeague() {
        return toLeague;
    }

    public void setToLeague(League toLeague) {
        this.toLeague = toLeague;
    }

    public DraftType getDraftType() {
        return draftType;
    }

    public void setDraftType(DraftType draftType) {
        this.draftType = draftType;
    }

    public Integer getDraftRound() {
        return draftRound;
    }

    public void setDraftRound(Integer draftRound) {
        this.draftRound = draftRound;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
