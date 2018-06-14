package com.ip.service;

/**
 * AllocationService interface provides abstract definition 
 * to allocate method
 * @author NEERAJ SWAMI
 *
 */
public interface AllocationService extends Runnable{
	
	String allocate(String macAddress);
	

}
