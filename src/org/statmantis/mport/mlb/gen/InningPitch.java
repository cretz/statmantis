//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.10.24 at 10:41:34 PM CDT 
//


package org.statmantis.mport.mlb.gen;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inningPitch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inningPitch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="sv_id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="start_speed" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="end_speed" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="sz_top" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="sz_bot" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="pfx_x" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="pfx_z" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="px" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="pz" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="x0" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="y0" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="z0" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="vx0" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="vy0" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="vz0" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="ax" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="ay" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="az" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="break_y" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="break_angle" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="break_length" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="pitch_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type_confidence" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="zone" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nasty" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="spin_dir" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="spin_rate" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="cc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inningPitch")
public class InningPitch {

    @XmlAttribute(name = "des")
    protected String des;
    @XmlAttribute(name = "id")
    protected Integer id;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "x")
    protected BigDecimal x;
    @XmlAttribute(name = "y")
    protected BigDecimal y;
    @XmlAttribute(name = "sv_id")
    protected String svId;
    @XmlAttribute(name = "start_speed")
    protected BigDecimal startSpeed;
    @XmlAttribute(name = "end_speed")
    protected BigDecimal endSpeed;
    @XmlAttribute(name = "sz_top")
    protected BigDecimal szTop;
    @XmlAttribute(name = "sz_bot")
    protected BigDecimal szBot;
    @XmlAttribute(name = "pfx_x")
    protected BigDecimal pfxX;
    @XmlAttribute(name = "pfx_z")
    protected BigDecimal pfxZ;
    @XmlAttribute(name = "px")
    protected BigDecimal px;
    @XmlAttribute(name = "pz")
    protected BigDecimal pz;
    @XmlAttribute(name = "x0")
    protected BigDecimal x0;
    @XmlAttribute(name = "y0")
    protected BigDecimal y0;
    @XmlAttribute(name = "z0")
    protected BigDecimal z0;
    @XmlAttribute(name = "vx0")
    protected BigDecimal vx0;
    @XmlAttribute(name = "vy0")
    protected BigDecimal vy0;
    @XmlAttribute(name = "vz0")
    protected BigDecimal vz0;
    @XmlAttribute(name = "ax")
    protected BigDecimal ax;
    @XmlAttribute(name = "ay")
    protected BigDecimal ay;
    @XmlAttribute(name = "az")
    protected BigDecimal az;
    @XmlAttribute(name = "break_y")
    protected BigDecimal breakY;
    @XmlAttribute(name = "break_angle")
    protected BigDecimal breakAngle;
    @XmlAttribute(name = "break_length")
    protected BigDecimal breakLength;
    @XmlAttribute(name = "pitch_type")
    protected String pitchType;
    @XmlAttribute(name = "type_confidence")
    protected BigDecimal typeConfidence;
    @XmlAttribute(name = "zone")
    protected Integer zone;
    @XmlAttribute(name = "nasty")
    protected Integer nasty;
    @XmlAttribute(name = "spin_dir")
    protected BigDecimal spinDir;
    @XmlAttribute(name = "spin_rate")
    protected BigDecimal spinRate;
    @XmlAttribute(name = "cc")
    protected String cc;

    /**
     * Gets the value of the des property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDes() {
        return des;
    }

    /**
     * Sets the value of the des property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDes(String value) {
        this.des = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the x property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX(BigDecimal value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setY(BigDecimal value) {
        this.y = value;
    }

    /**
     * Gets the value of the svId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvId() {
        return svId;
    }

    /**
     * Sets the value of the svId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvId(String value) {
        this.svId = value;
    }

    /**
     * Gets the value of the startSpeed property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartSpeed() {
        return startSpeed;
    }

    /**
     * Sets the value of the startSpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartSpeed(BigDecimal value) {
        this.startSpeed = value;
    }

    /**
     * Gets the value of the endSpeed property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEndSpeed() {
        return endSpeed;
    }

    /**
     * Sets the value of the endSpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEndSpeed(BigDecimal value) {
        this.endSpeed = value;
    }

    /**
     * Gets the value of the szTop property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSzTop() {
        return szTop;
    }

    /**
     * Sets the value of the szTop property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSzTop(BigDecimal value) {
        this.szTop = value;
    }

    /**
     * Gets the value of the szBot property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSzBot() {
        return szBot;
    }

    /**
     * Sets the value of the szBot property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSzBot(BigDecimal value) {
        this.szBot = value;
    }

    /**
     * Gets the value of the pfxX property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPfxX() {
        return pfxX;
    }

    /**
     * Sets the value of the pfxX property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPfxX(BigDecimal value) {
        this.pfxX = value;
    }

    /**
     * Gets the value of the pfxZ property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPfxZ() {
        return pfxZ;
    }

    /**
     * Sets the value of the pfxZ property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPfxZ(BigDecimal value) {
        this.pfxZ = value;
    }

    /**
     * Gets the value of the px property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPx() {
        return px;
    }

    /**
     * Sets the value of the px property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPx(BigDecimal value) {
        this.px = value;
    }

    /**
     * Gets the value of the pz property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPz() {
        return pz;
    }

    /**
     * Sets the value of the pz property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPz(BigDecimal value) {
        this.pz = value;
    }

    /**
     * Gets the value of the x0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX0() {
        return x0;
    }

    /**
     * Sets the value of the x0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX0(BigDecimal value) {
        this.x0 = value;
    }

    /**
     * Gets the value of the y0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getY0() {
        return y0;
    }

    /**
     * Sets the value of the y0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setY0(BigDecimal value) {
        this.y0 = value;
    }

    /**
     * Gets the value of the z0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZ0() {
        return z0;
    }

    /**
     * Sets the value of the z0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZ0(BigDecimal value) {
        this.z0 = value;
    }

    /**
     * Gets the value of the vx0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVx0() {
        return vx0;
    }

    /**
     * Sets the value of the vx0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVx0(BigDecimal value) {
        this.vx0 = value;
    }

    /**
     * Gets the value of the vy0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVy0() {
        return vy0;
    }

    /**
     * Sets the value of the vy0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVy0(BigDecimal value) {
        this.vy0 = value;
    }

    /**
     * Gets the value of the vz0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVz0() {
        return vz0;
    }

    /**
     * Sets the value of the vz0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVz0(BigDecimal value) {
        this.vz0 = value;
    }

    /**
     * Gets the value of the ax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAx() {
        return ax;
    }

    /**
     * Sets the value of the ax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAx(BigDecimal value) {
        this.ax = value;
    }

    /**
     * Gets the value of the ay property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAy() {
        return ay;
    }

    /**
     * Sets the value of the ay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAy(BigDecimal value) {
        this.ay = value;
    }

    /**
     * Gets the value of the az property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAz() {
        return az;
    }

    /**
     * Sets the value of the az property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAz(BigDecimal value) {
        this.az = value;
    }

    /**
     * Gets the value of the breakY property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBreakY() {
        return breakY;
    }

    /**
     * Sets the value of the breakY property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBreakY(BigDecimal value) {
        this.breakY = value;
    }

    /**
     * Gets the value of the breakAngle property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBreakAngle() {
        return breakAngle;
    }

    /**
     * Sets the value of the breakAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBreakAngle(BigDecimal value) {
        this.breakAngle = value;
    }

    /**
     * Gets the value of the breakLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBreakLength() {
        return breakLength;
    }

    /**
     * Sets the value of the breakLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBreakLength(BigDecimal value) {
        this.breakLength = value;
    }

    /**
     * Gets the value of the pitchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPitchType() {
        return pitchType;
    }

    /**
     * Sets the value of the pitchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPitchType(String value) {
        this.pitchType = value;
    }

    /**
     * Gets the value of the typeConfidence property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTypeConfidence() {
        return typeConfidence;
    }

    /**
     * Sets the value of the typeConfidence property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTypeConfidence(BigDecimal value) {
        this.typeConfidence = value;
    }

    /**
     * Gets the value of the zone property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getZone() {
        return zone;
    }

    /**
     * Sets the value of the zone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setZone(Integer value) {
        this.zone = value;
    }

    /**
     * Gets the value of the nasty property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNasty() {
        return nasty;
    }

    /**
     * Sets the value of the nasty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNasty(Integer value) {
        this.nasty = value;
    }

    /**
     * Gets the value of the spinDir property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpinDir() {
        return spinDir;
    }

    /**
     * Sets the value of the spinDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpinDir(BigDecimal value) {
        this.spinDir = value;
    }

    /**
     * Gets the value of the spinRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpinRate() {
        return spinRate;
    }

    /**
     * Sets the value of the spinRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpinRate(BigDecimal value) {
        this.spinRate = value;
    }

    /**
     * Gets the value of the cc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets the value of the cc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCc(String value) {
        this.cc = value;
    }

}
