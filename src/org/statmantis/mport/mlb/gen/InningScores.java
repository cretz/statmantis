//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.10.24 at 10:41:34 PM CDT 
//


package org.statmantis.mport.mlb.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inningScores complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inningScores">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="score" type="{http://gdx.mlb.com/components/schema/1}inningScore"/>
 *       &lt;/sequence>
 *       &lt;attribute name="away_team" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="home_team" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inningScores", propOrder = {
    "score"
})
public class InningScores {

    @XmlElement(required = true)
    protected InningScore score;
    @XmlAttribute(name = "away_team")
    protected String awayTeam;
    @XmlAttribute(name = "home_team")
    protected String homeTeam;

    /**
     * Gets the value of the score property.
     * 
     * @return
     *     possible object is
     *     {@link InningScore }
     *     
     */
    public InningScore getScore() {
        return score;
    }

    /**
     * Sets the value of the score property.
     * 
     * @param value
     *     allowed object is
     *     {@link InningScore }
     *     
     */
    public void setScore(InningScore value) {
        this.score = value;
    }

    /**
     * Gets the value of the awayTeam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAwayTeam() {
        return awayTeam;
    }

    /**
     * Sets the value of the awayTeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAwayTeam(String value) {
        this.awayTeam = value;
    }

    /**
     * Gets the value of the homeTeam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeTeam() {
        return homeTeam;
    }

    /**
     * Sets the value of the homeTeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeTeam(String value) {
        this.homeTeam = value;
    }

}
