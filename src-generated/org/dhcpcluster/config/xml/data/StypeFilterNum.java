//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.12.29 at 07:37:11 AM CET 
//


package org.dhcpcluster.config.xml.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * <p>Java class for stype-filter-num.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="stype-filter-num">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="eq"/>
 *     &lt;enumeration value="ne"/>
 *     &lt;enumeration value="lt"/>
 *     &lt;enumeration value="gt"/>
 *     &lt;enumeration value="le"/>
 *     &lt;enumeration value="ge"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum StypeFilterNum {

    @XmlEnumValue("eq")
    EQ("eq"),
    @XmlEnumValue("ne")
    NE("ne"),
    @XmlEnumValue("lt")
    LT("lt"),
    @XmlEnumValue("gt")
    GT("gt"),
    @XmlEnumValue("le")
    LE("le"),
    @XmlEnumValue("ge")
    GE("ge");
    private final String value;

    StypeFilterNum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StypeFilterNum fromValue(String v) {
        for (StypeFilterNum c: StypeFilterNum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
