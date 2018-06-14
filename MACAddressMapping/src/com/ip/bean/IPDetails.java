package com.ip.bean;

import java.time.format.DateTimeFormatter;

public class IPDetails {
	private long allocationStartedTime; // = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	  // LocalDateTime now = LocalDateTime.now();  
	private long allocationExpiryTime;
	
	private String ip;

	

	public long getAllocationStartedTime() {
		return allocationStartedTime;
	}

	public void setAllocationStartedTime(long allocationStartedTime) {
		this.allocationStartedTime = allocationStartedTime;
	}

	public long getAllocationExpiryTime() {
		return allocationExpiryTime;
	}

	public void setAllocationExpiryTime(long allocationExpiryTime) {
		this.allocationExpiryTime = allocationExpiryTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	

}
