
package introsde.assignment.soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for measureDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="measureDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idMeasureDef" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="measureName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="measureType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "measureDefinition", propOrder = {
    "idMeasureDef",
    "measureName",
    "measureType"
})
public class MeasureDefinition {

    protected int idMeasureDef;
    protected String measureName;
    protected String measureType;

    /**
     * Gets the value of the idMeasureDef property.
     * 
     */
    public int getIdMeasureDef() {
        return idMeasureDef;
    }

    /**
     * Sets the value of the idMeasureDef property.
     * 
     */
    public void setIdMeasureDef(int value) {
        this.idMeasureDef = value;
    }

    /**
     * Gets the value of the measureName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureName() {
        return measureName;
    }

    /**
     * Sets the value of the measureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureName(String value) {
        this.measureName = value;
    }

    /**
     * Gets the value of the measureType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureType() {
        return measureType;
    }

    /**
     * Sets the value of the measureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureType(String value) {
        this.measureType = value;
    }

}
