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

import static sf.dhcp4java.DHCPConstants.BOOTREQUEST;
import static sf.dhcp4java.DHCPConstants.HTYPE_ETHER;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import sf.dhcp4java.DHCPPacket;
import sf.dhcp4java.DHCPServer;
import sf.dhcp4java.DHCPServerInitException;

import static sf.dhcp4java.DHCPConstants.*;

/**
 * A simple client used for testing DHCPStaticServer under stress conditions.
 * 
 * @author Stephan Hadinger
 * @version 0.50
 */
public class DHCPStressTest {
	private static final Logger logger = Logger.getLogger("sf.dhcp4java.examples.dhcpstresstest");
	
	private DHCPPacket discover;
	private DHCPPacket request;
	private DatagramPacket discoverPacket;
	private DatagramPacket offerPacket = new DatagramPacket(new byte[1500], 1500);
	private DatagramPacket requestPacket;
	private DatagramPacket ackPacket = new DatagramPacket(new byte[1500], 1500);
    private DatagramSocket socket = null;
    
    private static final int NB_ITERATIONS = 100000;
    private static final String SERVER_ADDR = "127.0.0.1";
    private static final int SERVER_PORT = 6767;
    private static final String CLIENT_ADDR = "127.0.0.1";
    private static final int CLIENT_PORT = 6868;
	
	private void init() {
		try {
			InetAddress serverAddr = InetAddress.getByName(CLIENT_ADDR);
			
			discover = new DHCPPacket();
	
	        discover.setOp(BOOTREQUEST);
	        discover.setHtype(HTYPE_ETHER);
	        discover.setHlen((byte) 6);
	        discover.setHops((byte) 0);
	        discover.setXid( (new Random()).nextInt() );
	        discover.setSecs((short) 0);
	        discover.setFlags((short) 0);
	        discover.setChaddrHex("000802E7BFA5");
	        
	        discover.setDHCPMessageType(DHCPDISCOVER);
	        discover.setOptionAsString(DHO_VENDOR_CLASS_IDENTIFIER, "MSFT5.0");
	        
	        byte[] discoverBytes = discover.serialize();
	        discoverPacket = new DatagramPacket(discoverBytes, discoverBytes.length, serverAddr, SERVER_PORT);
	        
	        request = discover.clone();
	        request.setDHCPMessageType(DHCPREQUEST);
	        request.setCiaddr("10.0.0.1");
	        
	        byte[] requestBytes = request.serialize();
	        requestPacket = new DatagramPacket(requestBytes, requestBytes.length, serverAddr, SERVER_PORT);

	        socket = new DatagramSocket(CLIENT_PORT);
	        
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	
	private void run() {
		try {
			// warm-up
			
			for (int i=0; i<1000; i++) {
				socket.send(discoverPacket);
		        socket.receive(offerPacket);
		        
		        socket.send(requestPacket);
		        socket.receive(ackPacket);
			}
			
			Date timeBegin = new Date();
			for (int i=0; i<NB_ITERATIONS; i++) {
				socket.send(discoverPacket);
		        socket.receive(offerPacket);
		        
		        socket.send(requestPacket);
		        socket.receive(ackPacket);
			}
			
			Date timeEnd = new Date();
			long millis = timeEnd.getTime() - timeBegin.getTime();
			logger.info("Elapsed time "+millis/1000.0+" seconds");
			logger.info("Cycles/second "+(int) (NB_ITERATIONS*1000.0/millis));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unexpected exception", e);
		}
	}
	/**
	 * 
	 */
	private void startServer() throws DHCPServerInitException {
	    Properties stressProperties = new Properties();
	    stressProperties.put(DHCPServer.SERVER_ADDRESS, SERVER_ADDR+":"+SERVER_PORT);
	    stressProperties.put(DHCPServer.SERVER_THREADS, "1");
        DHCPServer server = DHCPServer.initServer(new DHCPStaticServer(), stressProperties);
        new Thread(server).start();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DHCPStressTest client = new DHCPStressTest();
		try {
			client.init();
			client.startServer();
			client.run();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unexpected exception", e);
		}
		System.exit(0);
	}

}