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
 * <p>Java class for boxscoreBatter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="boxscoreBatter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pos" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bo" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ab" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="po" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="r" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="bb" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="a" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="t" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="sf" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="h" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="e" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="d" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="hbp" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="so" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="hr" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="rbi" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="lob" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="fldg" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="sb" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="s_hr" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="s_rbi" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="avg" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boxscoreBatter")
public class BoxscoreBatter {

    @XmlAttribute(name = "id")
    protected Long id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "pos")
    protected String pos;
    @XmlAttribute(name = "bo")
    protected Integer bo;
    @XmlAttribute(name = "ab")
    protected Integer ab;
    @XmlAttribute(name = "po")
    protected Integer po;
    @XmlAttribute(name = "r")
    protected Integer r;
    @XmlAttribute(name = "bb")
    protected Integer bb;
    @XmlAttribute(name = "a")
    protected Integer a;
    @XmlAttribute(name = "t")
    protected Integer t;
    @XmlAttribute(name = "sf")
    protected Integer sf;
    @XmlAttribute(name = "h")
    protected Integer h;
    @XmlAttribute(name = "e")
    protected Integer e;
    @XmlAttribute(name = "d")
    protected Integer d;
    @XmlAttribute(name = "hbp")
    protected Integer hbp;
    @XmlAttribute(name = "so")
    protected Integer so;
    @XmlAttribute(name = "hr")
    protected Integer hr;
    @XmlAttribute(name = "rbi")
    protected Integer rbi;
    @XmlAttribute(name = "lob")
    protected Integer lob;
    @XmlAttribute(name = "fldg")
    protected BigDecimal fldg;
    @XmlAttribute(name = "sb")
    protected Integer sb;
    @XmlAttribute(name = "s_hr")
    protected Integer sHr;
    @XmlAttribute(name = "s_rbi")
    protected Integer sRbi;
    @XmlAttribute(name = "avg")
    protected BigDecimal avg;

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the bo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBo() {
        return bo;
    }

    /**
     * Sets the value of the bo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBo(Integer value) {
        this.bo = value;
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
     * Gets the value of the po property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPo() {
        return po;
    }

    /**
     * Sets the value of the po property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPo(Integer value) {
        this.po = value;
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
     * Gets the value of the bb property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBb() {
        return bb;
    }

    /**
     * Sets the value of the bb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBb(Integer value) {
        this.bb = value;
    }

    /**
     * Gets the value of the a property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getA() {
        return a;
    }

    /**
     * Sets the value of the a property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setA(Integer value) {
        this.a = value;
    }

    /**
     * Gets the value of the t property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getT() {
        return t;
    }

    /**
     * Sets the value of the t property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setT(Integer value) {
        this.t = value;
    }

    /**
     * Gets the value of the sf property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSf() {
        return sf;
    }

    /**
     * Sets the value of the sf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSf(Integer value) {
        this.sf = value;
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
     * Gets the value of the e property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getE() {
        return e;
    }

    /**
     * Sets the value of the e property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setE(Integer value) {
        this.e = value;
    }

    /**
     * Gets the value of the d property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getD() {
        return d;
    }

    /**
     * Sets the value of the d property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setD(Integer value) {
        this.d = value;
    }

    /**
     * Gets the value of the hbp property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHbp() {
        return hbp;
    }

    /**
     * Sets the value of the hbp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHbp(Integer value) {
        this.hbp = value;
    }

    /**
     * Gets the value of the so property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSo() {
        return so;
    }

    /**
     * Sets the value of the so property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSo(Integer value) {
        this.so = value;
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
     * Gets the value of the lob property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLob() {
        return lob;
    }

    /**
     * Sets the value of the lob property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLob(Integer value) {
        this.lob = value;
    }

    /**
     * Gets the value of the fldg property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFldg() {
        return fldg;
    }

    /**
     * Sets the value of the fldg property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFldg(BigDecimal value) {
        this.fldg = value;
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

    /**
     * Gets the value of the sHr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSHr() {
        return sHr;
    }

    /**
     * Sets the value of the sHr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSHr(Integer value) {
        this.sHr = value;
    }

    /**
     * Gets the value of the sRbi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSRbi() {
        return sRbi;
    }

    /**
     * Sets the value of the sRbi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSRbi(Integer value) {
        this.sRbi = value;
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

}
