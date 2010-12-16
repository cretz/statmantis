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
 * Representation of a pitch
 *
 * @author Chad Retz
 */
@Entity
@Table(name = "Pitch")
@SuppressWarnings("serial")
public class Pitch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PitchId", unique = true, nullable = false)
    private long pitchId;
    
    @Column(name = "CameraX")
    Float cameraX;
    
    @Column(name = "CameraZ")
    Float cameraZ;
    
    @Column(name = "StartSpeed")
    Float startSpeed;
    
    @Column(name = "EndSpeed")
    Float endSpeed;
    
    @Column(name = "StrikeZoneTop")
    Float strikeZoneTop;
    
    @Column(name = "StrikeZoneBottom")
    Float strikeZoneBottom;
    
    @Column(name = "DeviationX")
    Float deviationX;
    
    @Column(name = "DeviationY")
    Float deviationY;
    
    @Column(name = "PitchX")
    Float pitchX;
    
    @Column(name = "PitchZ")
    Float pitchZ;
    
    @Column(name = "InitialX")
    Float initialX;
    
    @Column(name = "InitialY")
    Float initialY;
    
    @Column(name = "InitialZ")
    Float initialZ;
    
    @Column(name = "InitialVelocityX")
    Float initialVelocityX;
    
    @Column(name = "InitialVelocityY")
    Float initialVelocityY;
    
    @Column(name = "InitialVelocityZ")
    Float initialVelocityZ;
    
    @Column(name = "AccelerationX")
    Float accelerationX;
    
    @Column(name = "AccelerationY")
    Float accelerationY;
    
    @Column(name = "AccelerationZ")
    Float accelerationZ;
    
    @Column(name = "BreakY")
    Float breakY;
    
    @Column(name = "BreakAngle")
    Float breakAngle;
    
    @Column(name = "BreakLength")
    Float breakLength;
    
    @Column(name = "Description")
    String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PitchType")
    Float pitchType;
    
    @Column(name = "TypeConfidence")
    Float typeConfidence;
    
    @Column(name = "Zone")
    Integer zone;
    
    @Column(name = "Nasty")
    Integer nasty;
    
    @Column(name = "SpinDirection")
    Float spinDirection;
    
    @Column(name = "SpinRate")
    Float spinRate;

    public long getPitchId() {
        return pitchId;
    }

    public void setPitchId(long pitchId) {
        this.pitchId = pitchId;
    }

    public Float getCameraX() {
        return cameraX;
    }

    public void setCameraX(Float cameraX) {
        this.cameraX = cameraX;
    }

    public Float getCameraZ() {
        return cameraZ;
    }

    public void setCameraZ(Float cameraZ) {
        this.cameraZ = cameraZ;
    }

    public Float getStartSpeed() {
        return startSpeed;
    }

    public void setStartSpeed(Float startSpeed) {
        this.startSpeed = startSpeed;
    }

    public Float getEndSpeed() {
        return endSpeed;
    }

    public void setEndSpeed(Float endSpeed) {
        this.endSpeed = endSpeed;
    }

    public Float getStrikeZoneTop() {
        return strikeZoneTop;
    }

    public void setStrikeZoneTop(Float strikeZoneTop) {
        this.strikeZoneTop = strikeZoneTop;
    }

    public Float getStrikeZoneBottom() {
        return strikeZoneBottom;
    }

    public void setStrikeZoneBottom(Float strikeZoneBottom) {
        this.strikeZoneBottom = strikeZoneBottom;
    }

    public Float getDeviationX() {
        return deviationX;
    }

    public void setDeviationX(Float deviationX) {
        this.deviationX = deviationX;
    }

    public Float getDeviationY() {
        return deviationY;
    }

    public void setDeviationY(Float deviationY) {
        this.deviationY = deviationY;
    }

    public Float getPitchX() {
        return pitchX;
    }

    public void setPitchX(Float pitchX) {
        this.pitchX = pitchX;
    }

    public Float getPitchZ() {
        return pitchZ;
    }

    public void setPitchZ(Float pitchZ) {
        this.pitchZ = pitchZ;
    }

    public Float getInitialX() {
        return initialX;
    }

    public void setInitialX(Float initialX) {
        this.initialX = initialX;
    }

    public Float getInitialY() {
        return initialY;
    }

    public void setInitialY(Float initialY) {
        this.initialY = initialY;
    }

    public Float getInitialZ() {
        return initialZ;
    }

    public void setInitialZ(Float initialZ) {
        this.initialZ = initialZ;
    }

    public Float getInitialVelocityX() {
        return initialVelocityX;
    }

    public void setInitialVelocityX(Float initialVelocityX) {
        this.initialVelocityX = initialVelocityX;
    }

    public Float getInitialVelocityY() {
        return initialVelocityY;
    }

    public void setInitialVelocityY(Float initialVelocityY) {
        this.initialVelocityY = initialVelocityY;
    }

    public Float getInitialVelocityZ() {
        return initialVelocityZ;
    }

    public void setInitialVelocityZ(Float initialVelocityZ) {
        this.initialVelocityZ = initialVelocityZ;
    }

    public Float getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(Float accelerationX) {
        this.accelerationX = accelerationX;
    }

    public Float getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(Float accelerationY) {
        this.accelerationY = accelerationY;
    }

    public Float getAccelerationZ() {
        return accelerationZ;
    }

    public void setAccelerationZ(Float accelerationZ) {
        this.accelerationZ = accelerationZ;
    }

    public Float getBreakY() {
        return breakY;
    }

    public void setBreakY(Float breakY) {
        this.breakY = breakY;
    }

    public Float getBreakAngle() {
        return breakAngle;
    }

    public void setBreakAngle(Float breakAngle) {
        this.breakAngle = breakAngle;
    }

    public Float getBreakLength() {
        return breakLength;
    }

    public void setBreakLength(Float breakLength) {
        this.breakLength = breakLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPitchType() {
        return pitchType;
    }

    public void setPitchType(Float pitchType) {
        this.pitchType = pitchType;
    }

    public Float getTypeConfidence() {
        return typeConfidence;
    }

    public void setTypeConfidence(Float typeConfidence) {
        this.typeConfidence = typeConfidence;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Integer getNasty() {
        return nasty;
    }

    public void setNasty(Integer nasty) {
        this.nasty = nasty;
    }

    public Float getSpinDirection() {
        return spinDirection;
    }

    public void setSpinDirection(Float spinDirection) {
        this.spinDirection = spinDirection;
    }

    public Float getSpinRate() {
        return spinRate;
    }

    public void setSpinRate(Float spinRate) {
        this.spinRate = spinRate;
    }
}
