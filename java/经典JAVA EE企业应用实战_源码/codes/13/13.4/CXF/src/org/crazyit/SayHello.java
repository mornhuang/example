
package org.crazyit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sayHello complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sayHello">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://blog.crazyit.org}somebd" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sayHello", propOrder = {
    "somebd"
})
public class SayHello {

    @XmlElement(namespace = "http://blog.crazyit.org")
    protected Person_Type somebd;

    /**
     * Gets the value of the somebd property.
     * 
     * @return
     *     possible object is
     *     {@link Person_Type }
     *     
     */
    public Person_Type getSomebd() {
        return somebd;
    }

    /**
     * Sets the value of the somebd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person_Type }
     *     
     */
    public void setSomebd(Person_Type value) {
        this.somebd = value;
    }

}
