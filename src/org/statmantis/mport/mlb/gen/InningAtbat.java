//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.10.24 at 10:41:34 PM CDT 
//


package org.statmantis.mport.mlb.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inningAtbat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inningAtbat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pitch" type="{http://gdx.mlb.com/components/schema/1}inningPitch" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="runner" type="{http://gdx.mlb.com/components/schema/1}inningRunner" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="num" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="b" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="s" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="o" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="start_tfs" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="batter" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="stand" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="b_height" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pitcher" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="p_throws" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="event" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inningAtbat", propOrder = {
    "pitch",
    "runner"
})
public class InningAtbat {

    protected List<InningPitch> pitch;
    protected List<InningRunner> runner;
    @XmlAttribute(name = "num")
    protected Integer num;
    @XmlAttribute(name = "b")
    protected Integer b;
    @XmlAttribute(name = "s")
    protected Integer s;
    @XmlAttribute(name = "o")
    protected Integer o;
    @XmlAttribute(name = "start_tfs")
    protected Long startTfs;
    @XmlAttribute(name = "batter")
    protected Long batter;
    @XmlAttribute(name = "stand")
    protected String stand;
    @XmlAttribute(name = "b_height")
    protected String bHeight;
    @XmlAttribute(name = "pitcher")
    protected Long pitcher;
    @XmlAttribute(name = "p_throws")
    protected String pThrows;
    @XmlAttribute(name = "des")
    protected String des;
    @XmlAttribute(name = "event")
    protected String event;

    /**
     * Gets the value of the pitch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pitch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPitch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InningPitch }
     * 
     * 
     */
    public List<InningPitch> getPitch() {
        if (pitch == null) {
            pitch = new ArrayList<InningPitch>();
        }
        return this.pitch;
    }

    /**
     * Gets the value of the runner property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the runner property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRunner().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InningRunner }
     * 
     * 
     */
    public List<InningRunner> getRunner() {
        if (runner == null) {
            runner = new ArrayList<InningRunner>();
        }
        return this.runner;
    }

    /**
     * Gets the value of the num property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNum() {
        return num;
    }

    /**
     * Sets the value of the num property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNum(Integer value) {
        this.num = value;
    }

    /**
     * Gets the value of the b property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getB() {
        return b;
    }

    /**
     * Sets the value of the b property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setB(Integer value) {
        this.b = value;
    }

    /**
     * Gets the value of the s property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getS() {
        return s;
    }

    /**
     * Sets the value of the s property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setS(Integer value) {
        this.s = value;
    }

    /**
     * Gets the value of the o property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getO() {
        return o;
    }

    /**
     * Sets the value of the o property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setO(Integer value) {
        this.o = value;
    }

    /**
     * Gets the value of the startTfs property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartTfs() {
        return startTfs;
    }

    /**
     * Sets the value of the startTfs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartTfs(Long value) {
        this.startTfs = value;
    }

    /**
     * Gets the value of the batter property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBatter() {
        return batter;
    }

    /**
     * Sets the value of the batter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBatter(Long value) {
        this.batter = value;
    }

    /**
     * Gets the value of the stand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStand() {
        return stand;
    }

    /**
     * Sets the value of the stand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStand(String value) {
        this.stand = value;
    }

    /**
     * Gets the value of the bHeight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBHeight() {
        return bHeight;
    }

    /**
     * Sets the value of the bHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBHeight(String value) {
        this.bHeight = value;
    }

    /**
     * Gets the value of the pitcher property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPitcher() {
        return pitcher;
    }

    /**
     * Sets the value of the pitcher property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPitcher(Long value) {
        this.pitcher = value;
    }

    /**
     * Gets the value of the pThrows property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPThrows() {
        return pThrows;
    }

    /**
     * Sets the value of the pThrows property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPThrows(String value) {
        this.pThrows = value;
    }

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
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvent(String value) {
        this.event = value;
    }

}
