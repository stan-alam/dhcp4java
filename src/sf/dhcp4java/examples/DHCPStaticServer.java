/*
 *	This file is part of dhcp4java.
 *
 *	dhcp4java is free software; you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation; either version 2 of the License, or
 *	(at your option) any later version.
 *
 *	dhcp4java is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with Foobar; if not, write to the Free Software
 *	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * (c) 2006 Stephan Hadinger
 */
package sf.dhcp4java.examples;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import sf.dhcp4java.DHCPOption;
import sf.dhcp4java.DHCPPacket;
import sf.dhcp4java.DHCPServer;
import sf.dhcp4java.DHCPServlet;
import sf.dhcp4java.DHCPServerInitException;

import static sf.dhcp4java.DHCPConstants.*;


/**
 * A sample DHCP servlet (under construction).
 * 
 * @author Stephan Hadinger
 * @version 0.50
 */
public class DHCPStaticServer extends DHCPServlet {

    private static final Logger logger = Logger.getLogger("sf.dhcp4java.examples.trivialdhcpservlet");
    
    private HashMap<String, InetAddress> macIpMap = new HashMap<String, InetAddress>();
    DHCPOption[] commonOptions = null;
    
    private static final String CLIENT_MAC_PREFIX = "client.";
    
    /* (non-Javadoc)
	 * @see sf.dhcp4java.DHCPServlet#init(java.util.Properties)
	 */
	@Override
	public void init(Properties props) {
		// we create a dummy packet to extract "common options"
		DHCPPacket temp = new DHCPPacket();
		try {
			// parse all properties to extract static client definitions
			for (Object keyObject : props.keySet()) {
				String key = ((String) keyObject).toLowerCase();
				if (key.startsWith(CLIENT_MAC_PREFIX)) {
					String addrString = (String) props.get(keyObject);
					try {
						InetAddress addr = InetAddress.getByName(addrString);
						macIpMap.put(key.substring(CLIENT_MAC_PREFIX.length()), addr);
					} catch (UnknownHostException e) {
						logger.log(Level.SEVERE, "Could not parse InetAddress "+addrString, e);
					}
				}
			}
			
			temp.setOptionAsInetAddress(DHO_DHCP_SERVER_IDENTIFIER, "192.168.1.1");
			temp.setOptionAsInt(DHO_DHCP_LEASE_TIME, 86400);
			temp.setOptionAsInetAddress(DHO_SUBNET_MASK, "255.255.252.0");
			temp.setOptionAsInetAddress(DHO_ROUTERS, "10.0.0.10");
			temp.setOptionAsInetAddress(DHO_STATIC_ROUTES, "172.20.224.167");
			temp.setOptionAsInetAddress(DHO_NTP_SERVERS, "10.0.0.10");
			temp.setOptionAsInetAddress(DHO_WWW_SERVER, "10.0.0.10");
			// store options in a instance array
			commonOptions = temp.getOptionsArray();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see sf.dhcp4java.DHCPServlet#doDiscover(sf.dhcp4java.DHCPPacket)
	 */
	@Override
	protected DHCPPacket doDiscover(DHCPPacket request) {
		InetAddress clientIp = calcAddrFromMac(request);
		if (clientIp == null)	return null;
		
		DHCPPacket response = request.clone();
		response.setOp(BOOTREPLY);
		response.setCiaddr(clientIp);
		response.setDHCPMessageType(DHCPOFFER);
		response.setOptions(commonOptions);

		return response;
	}

	/* (non-Javadoc)
	 * @see sf.dhcp4java.DHCPServlet#doRequest(sf.dhcp4java.DHCPPacket)
	 */
	@Override
	protected DHCPPacket doRequest(DHCPPacket request) {
		InetAddress clientIp = calcAddrFromMac(request);
		if (clientIp == null)	return null;
		
		DHCPPacket response = request.clone();
		response.setOp(BOOTREPLY);
		if (request.getCiaddr().equals(clientIp)) {
			response.setDHCPMessageType(DHCPACK);
		} else {
			response.setDHCPMessageType(DHCPNAK);
		}
		response.setOptions(commonOptions);

		return response;
	}

	/**
	 * Calculate address from packet and @MAC Address.
	 * 
	 * @param request
	 * @return address for client, or null if ignore
	 */
	private InetAddress calcAddrFromMac(DHCPPacket request) {
		// check vendor class
		String vendor = request.getOptionAsString(DHO_VENDOR_CLASS_IDENTIFIER);
		if ((vendor == null) || (vendor.indexOf("MSFT5.0") < 0))
			return null;		// only Microsoft vendor class

		// check @MAC address format
		if ((request.getHtype() != HTYPE_ETHER) && (request.getHlen() != 6))
			return null;
		
		// look for map
		InetAddress clientIp = macIpMap.get(request.getChaddrAsHex().toLowerCase());
		if (clientIp == null) return null;		// not found
		
		return clientIp;
	}
	/**
	 * Server launcher.
	 * 
	 * @param args command line arguments - ignored
	 */
	public static void main(String[] args) {
        try {
            DHCPServer server = DHCPServer.initServer(new DHCPStaticServer(), null);
            logger.setLevel(Level.OFF);
            new Thread(server).start();
        } catch (DHCPServerInitException e) {
            logger.log(Level.SEVERE, "Server init", e);
        }
    }
}