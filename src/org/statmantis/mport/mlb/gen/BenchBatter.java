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
 * <p>Java class for benchBatter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="benchBatter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="last" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="b" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pos" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="avg" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="g" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ab" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="r" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="h" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="hr" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="rbi" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="sb" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "benchBatter")
public class BenchBatter {

    @XmlAttribute(name = "id")
    protected Long id;
    @XmlAttribute(name = "last")
    protected String last;
    @XmlAttribute(name = "b")
    protected String b;
    @XmlAttribute(name = "pos")
    protected String pos;
    @XmlAttribute(name = "avg")
    protected BigDecimal avg;
    @XmlAttribute(name = "g")
    protected Integer g;
    @XmlAttribute(name = "ab")
    protected Integer ab;
    @XmlAttribute(name = "r")
    protected Integer r;
    @XmlAttribute(name = "h")
    protected Integer h;
    @XmlAttribute(name = "hr")
    protected Integer hr;
    @XmlAttribute(name = "rbi")
    protected Integer rbi;
    @XmlAttribute(name = "sb")
    protected Integer sb;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the last property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLast() {
        return last;
    }

    /**
     * Sets the value of the last property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLast(String value) {
        this.last = value;
    }

    /**
     * Gets the value of the b property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getB() {
        return b;
    }

    /**
     * Sets the value of the b property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setB(String value) {
        this.b = value;
    }

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPos() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPos(String value) {
        this.pos = value;
    }

    /**
     * Gets the value of the avg property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAvg() {
        return avg;
    }

    /**
     * Sets the value of the avg property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAvg(BigDecimal value) {
        this.avg = value;
    }

    /**
     * Gets the value of the g property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getG() {
        return g;
    }

    /**
     * Sets the value of the g property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setG(Integer value) {
        this.g = value;
    }

    /**
     * Gets the value of the ab property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAb() {
        return ab;
    }

    /**
     * Sets the value of the ab property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAb(Integer value) {
        this.ab = value;
    }

    /**
     * Gets the value of the r property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getR() {
        return r;
    }

    /**
     * Sets the value of the r property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setR(Integer value) {
        this.r = value;
    }

    /**
     * Gets the value of the h property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getH() {
        return h;
    }

    /**
     * Sets the value of the h property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setH(Integer value) {
        this.h = value;
    }

    /**
     * Gets the value of the hr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHr() {
        return hr;
    }

    /**
     * Sets the value of the hr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHr(Integer value) {
        this.hr = value;
    }

    /**
     * Gets the value of the rbi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRbi() {
        return rbi;
    }

    /**
     * Sets the value of the rbi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRbi(Integer value) {
        this.rbi = value;
    }

    /**
     * Gets the value of the sb property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSb() {
        return sb;
    }

    /**
     * Sets the value of the sb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSb(Integer value) {
        this.sb = value;
    }

}