//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.12.29 at 07:37:11 AM CET 
//


package org.dhcpcluster.config.xml.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type-filter-root complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type-filter-root">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type-filter-root")
@XmlSeeAlso({
    org.dhcpcluster.config.xml.data.Filter.FilterAnd.class,
    org.dhcpcluster.config.xml.data.Filter.FilterOr.class,
    org.dhcpcluster.config.xml.data.Filter.FilterNot.class,
    org.dhcpcluster.config.xml.data.Filter.FilterAlwaysTrue.class,
    org.dhcpcluster.config.xml.data.Filter.FilterAlwaysFalse.class,
    org.dhcpcluster.config.xml.data.Filter.FilterNumOption.class,
    org.dhcpcluster.config.xml.data.Filter.FilterStringOption.class
})
public class TypeFilterRoot {


}
