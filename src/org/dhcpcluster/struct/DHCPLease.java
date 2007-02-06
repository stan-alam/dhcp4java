/*
 *	This file is part of dhcp4java, a DHCP API for the Java language.
 *	(c) 2006 Stephan Hadinger
 *
 *	This library is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU Lesser General Public
 *	License as published by the Free Software Foundation; either
 *	version 2.1 of the License, or (at your option) any later version.
 *
 *	This library is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *	Lesser General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public
 *	License along with this library; if not, write to the Free Software
 *	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.dhcpcluster.struct;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Logger;

/**
 * This class represent a DHCP Lease given to a client.
 * 
 * <p>This class is basically a databean and has very little business logic.
 * 
 * @author Stephan Hadinger
 * @version 0.71
 */
public class DHCPLease implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(DHCPLease.class.getName().toLowerCase());

    public enum Status {
    	
    	FREE(0),
    	RESERVED(1),
    	OTHER(2);
    	
    	private final int code;
    	
    	Status(int code) {
    		this.code = code;
    	}
    	public int getCodr() {
    		return code;
    	}
    	public static Status fromInt(int code) {
    		for (Status s : Status.values()) {
    			if (s.code == code) {
    				return s;
    			}
    		}
    		throw new IllegalArgumentException("code="+code+" is not a valid DHCPLease.Status");
    	}
    }
    
    private Date creationDate;
    private Date updateDate;
    private Date expirationDate;
    
    private long ip;
    private byte[] mac;
    private Status status;
//
//	Date creationDate = res.getDate("CREATION_DATE");
//	Date updateDate = res.getDate("UPDATE_DATE");
//	Date expirationDate = res.getDate("EXPIRATION_DATE");
    
    private long classId;
    private long nodeId;
    private long profileId;
    private long concentratorId;
    private long subnetId;
    private long subnetIp;
    private long maskIp;
    private long gatewayIp;
    
    public DHCPLease() {
    	
    }

	/**
	 * @return Returns the classId.
	 */
	public long getClassId() {
		return classId;
	}

	/**
	 * @param classId The classId to set.
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}

	/**
	 * @return Returns the concentratorId.		// TODO what is a concentratorId ?
	 */
	public long getConcentratorId() {
		return concentratorId;
	}

	/**
	 * @param concentratorId The concentratorId to set.		// TODO what is a concentratorId ?
	 */
	public void setConcentratorId(long concentratorId) {
		this.concentratorId = concentratorId;
	}

	/**
	 * @return Returns the gatewayIp.			// gateway (giaddr) associated to the Lease
	 */
	public long getGatewayIp() {
		return gatewayIp;
	}

	/**
	 * @param gatewayIp The gatewayIp to set.
	 */
	public void setGatewayIp(long gatewayIp) {
		this.gatewayIp = gatewayIp;
	}

	/**
	 * @return Returns the maskIp.
	 */
	public long getMaskIp() {
		return maskIp;
	}

	/**
	 * @param maskIp The maskIp to set.
	 */
	public void setMaskIp(long maskIp) {
		this.maskIp = maskIp;
	}

	/**
	 * @return Returns the nodeId.
	 */
	public long getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId The nodeId to set.
	 */
	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return Returns the profileId.
	 */
	public long getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId The profileId to set.
	 */
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	/**
	 * @return Returns the subnetId.
	 */
	public long getSubnetId() {
		return subnetId;
	}

	/**
	 * @param subnetId The subnetId to set.
	 */
	public void setSubnetId(long subnetId) {
		this.subnetId = subnetId;
	}

	/**
	 * @return Returns the subnetIp.
	 */
	public long getSubnetIp() {
		return subnetIp;
	}

	/**
	 * @param subnetIp The subnetIp to set.
	 */
	public void setSubnetIp(long subnetIp) {
		this.subnetIp = subnetIp;
	}

	/**
	 * @return Returns the creationDate.
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate The creationDate to set.
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return Returns the expirationDate.
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate The expirationDate to set.
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return Returns the updateDate.
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate The updateDate to set.
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return Returns the ip.
	 */
	public long getIp() {
		return ip;
	}

	/**
	 * @param ip The ip to set.
	 */
	public void setIp(long ip) {
		this.ip = ip;
	}

	/**
	 * @return Returns the mac.
	 */
	public byte[] getMac() {
		return mac;
	}

	/**
	 * @param mac The mac to set.
	 */
	public void setMac(byte[] mac) {
		this.mac = mac;
	}

	/**
	 * @return Returns the status.
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
        
}
