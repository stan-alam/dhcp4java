//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.12.28 at 06:14:30 AM CET 
//


package org.dhcpcluster.config.xml.data;

import java.net.InetAddress;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter3
    extends XmlAdapter<String, InetAddress>
{


    public InetAddress unmarshal(String value) {
        return (org.dhcpcluster.config.xml.Util.parseInetAddress(value));
    }

    public String marshal(InetAddress value) {
        return (org.dhcpcluster.config.xml.Util.printInetAddress(value));
    }

}