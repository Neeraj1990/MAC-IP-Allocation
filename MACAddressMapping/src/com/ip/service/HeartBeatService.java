package com.ip.service;
/**
 * HeartBeatService interface provides abstract definition 
 * to refresh method
 * @author NEERAJ SWAMI
 *
 */
public interface HeartBeatService {
	boolean refresh(String macAddress, String allocatedIPAddress);

}
