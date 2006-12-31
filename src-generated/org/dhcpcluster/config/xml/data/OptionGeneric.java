//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.12.31 at 06:28:58 AM CET 
//


package org.dhcpcluster.config.xml.data;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for option-generic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="option-generic">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{}value-byte"/>
 *         &lt;element ref="{}value-short"/>
 *         &lt;element ref="{}value-int"/>
 *         &lt;element ref="{}value-inet"/>
 *         &lt;element ref="{}value-rawhex"/>
 *         &lt;element ref="{}value-raw64"/>
 *         &lt;element ref="{}value-string"/>
 *         &lt;element ref="{}value-string-element"/>
 *       &lt;/choice>
 *       &lt;attribute name="mirror" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "option-generic", propOrder = {
    "valueByteOrValueShortOrValueInt"
})
@XmlSeeAlso({
    TypeOptionInets.class,
    TypeOptionInet.class,
    TypeOptionBytes.class,
    TypeOptionByte.class,
    TypeOptionInt.class,
    TypeOptionShort.class,
    TypeOptionShorts.class,
    TypeOptionString.class,
    Option.class
})
public class OptionGeneric {

    @XmlElementRefs({
        @XmlElementRef(name = "value-string", type = JAXBElement.class),
        @XmlElementRef(name = "value-inet", type = JAXBElement.class),
        @XmlElementRef(name = "value-raw64", type = JAXBElement.class),
        @XmlElementRef(name = "value-short", type = JAXBElement.class),
        @XmlElementRef(name = "value-rawhex", type = JAXBElement.class),
        @XmlElementRef(name = "value-string-element", type = JAXBElement.class),
        @XmlElementRef(name = "value-byte", type = JAXBElement.class),
        @XmlElementRef(name = "value-int", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> valueByteOrValueShortOrValueInt;
    @XmlAttribute
    protected Boolean mirror;

    /**
     * Gets the value of the valueByteOrValueShortOrValueInt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueByteOrValueShortOrValueInt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueByteOrValueShortOrValueInt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link InetAddress }{@code >}
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * {@link JAXBElement }{@code <}{@link Short }{@code >}
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Byte }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getValueByteOrValueShortOrValueInt() {
        if (valueByteOrValueShortOrValueInt == null) {
            valueByteOrValueShortOrValueInt = new ArrayList<JAXBElement<?>>();
        }
        return this.valueByteOrValueShortOrValueInt;
    }

    /**
     * Gets the value of the mirror property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMirror() {
        if (mirror == null) {
            return false;
        } else {
            return mirror;
        }
    }

    /**
     * Sets the value of the mirror property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMirror(Boolean value) {
        this.mirror = value;
    }

}
