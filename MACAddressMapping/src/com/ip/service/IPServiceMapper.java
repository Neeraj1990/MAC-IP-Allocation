package com.ip.service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.ip.bean.IPDetails;
import com.ip.util.IPConstants;
/**
 * 
 * IPServiceMapper controller class of Application 
 * Business implementation achieved inside this class
 * @author NEERAJ SWAMI 
 *
 */
public class IPServiceMapper implements AllocationService, HeartBeatService,
		Runnable {
	public static Map<String, IPDetails> ipContainer = new ConcurrentHashMap<String, IPDetails>();
	public static Set<AClassIP> freedIP = new HashSet<>();
	IPDetails ipdetails = new IPDetails();

	/**
	 * This checks for MAC Address assigned with any IP whether eligible to be freed
	 * If yes then assigned to Container Set freedID.
	 * @return : void
	 */
	public void run() {
		System.out.println("Thread is triggered");
		long allocationStartedTime = 0L, allocationExpiryTime = 0L;
		while (true) {
			for (Map.Entry<String, IPDetails> entry : ipContainer.entrySet()) {

				allocationExpiryTime = entry.getValue()
						.getAllocationExpiryTime();
				allocationStartedTime = entry.getValue()
						.getAllocationStartedTime();

				if (System.currentTimeMillis() >= allocationExpiryTime) {
					String ipArr[] = entry.getValue().getIp().split(":");
					AClassIP absoleteIP = new AClassIP();
					absoleteIP.setW(Integer.parseInt(ipArr[0]));
					absoleteIP.setX(Integer.parseInt(ipArr[1]));
					absoleteIP.setY(Integer.parseInt(ipArr[2]));
					absoleteIP.setZ(Integer.parseInt(ipArr[3]));
					freedIP.add(absoleteIP);
					ipContainer.remove(entry.getKey());
					
				}

			}try{
			Thread.sleep(IPConstants.THREAD_SLEEP_IN_MILLISEC);
			}
			catch(InterruptedException ex){
				ex.printStackTrace();
			}
			
		}
		
	}
 /**
  * This is just a utility method only to print data in freedIP Set container
  *  @return : void
  */
	static void getFreedSet()
	{
		System.out.println("Freed Set " +  freedIP);
	}
	
	/**
	 * Description : This method take macAddress as input 
	 * as per the business assign Allocate IP to every input
	 * @param : String 
	 * @return : String
	 */
	public String allocate(String macAddress) {
		if(macAddress == null  || macAddress.length() ==0 )
		{
			return "MACAddress should not be blank or null";
		}
		Calendar cal = Calendar.getInstance();
		AClassIP ip = new AClassIP();
		if (ipContainer.size() == 0) {
			ip.setW(IPConstants.OCTET_MIN_A);
			ip.setX(IPConstants.OCTET_MIN_B);
			ip.setY(IPConstants.OCTET_MIN_C);
			ip.setZ(IPConstants.OCTET_MIN_D);
			Date dt = new Date();
			cal.setTime(dt);
			ipdetails.setAllocationStartedTime(cal.getTimeInMillis());
			cal.add(Calendar.SECOND, IPConstants.IP_EXPIRES_TIME_IN_SEC);
			ipdetails.setAllocationExpiryTime(cal.getTimeInMillis());
			ipdetails.setIp(ip.toString());
			ipContainer.put(macAddress, ipdetails);

		} else {
			
			Date dt = new Date();
			cal.setTime(dt);
			ipdetails.setAllocationStartedTime(cal.getTimeInMillis());
			cal.add(Calendar.SECOND, IPConstants.IP_EXPIRES_TIME_IN_SEC);
			ipdetails.setAllocationExpiryTime(cal.getTimeInMillis());
			if (freedIP.size() != 0)
			{
				ip = (AClassIP)freedIP.toArray()[0];
				freedIP.remove(0);
			}
			else
			{
				ip = IPServiceMapper.generateIP(ipContainer);
			}
			 
			ipdetails.setIp(ip.toString());
			ipContainer.put(macAddress, ipdetails);
		}
		
		IPServiceMapper.getFreedSet();
		return ipdetails.getIp().toString();
	}

	/**
	 * This method get input and refresh expiry time of every allocatedIPAdress for mapped MACAddress
	 * if no MACAddress found in container then it will return false
	 * @param: String
	 * @param : String
	 * @return : boolean
	 */
	public boolean refresh(String macAddress, String allocatedIPAddress) {
		
		if(this.ipContainer.containsKey(macAddress))
		{
			long currentTime = ipContainer.get(macAddress).getAllocationExpiryTime();
			ipContainer.get(macAddress).setAllocationExpiryTime(currentTime + IPConstants.INCREASE_EXPIRY_TIME_IN_MILLISECONDS);
			System.out.println("Allocated IPAddress expriy time has been increased for given MACAddress :" + macAddress);
			return true; 
		}
		else{
			System.out.println("Input Mac is not assigned with any IP");
			return false;
		}
	}

	/**
	 * This is utility method to get valid IP to be assigned to input MACAddress
	 * @param :  map
	 * @return : AClassIP
	 */
	private static AClassIP generateIP(Map<String, IPDetails> map) {
		int mapSize = map.size();
		int tempLocal;
		Set<Map.Entry<String, IPDetails>> mapSet = map.entrySet();
		Map.Entry<String, IPDetails> lastElement = (Map.Entry<String, IPDetails>) mapSet
				.toArray()[mapSize - 1];
		IPDetails ipdata = lastElement.getValue();
		String ipString = ipdata.getIp();
		String[] ipArray = ipString.split(":");
		AClassIP ip = new AClassIP();
		ip.setW(Integer.parseInt(ipArray[0]));
		ip.setX(Integer.parseInt(ipArray[1]));
		ip.setY(Integer.parseInt(ipArray[2]));
		ip.setZ(Integer.parseInt(ipArray[3]));
		if (ip.getZ() < IPConstants.OCTET_MAX_D) {
			tempLocal = ip.getZ() + 1;
			ip.setZ(tempLocal);
		} else if (ip.getY() < IPConstants.OCTET_MAX_C) {
			tempLocal = ip.getY() + 1;
			ip.setY(tempLocal);
			return ip;
		}

		else if (ip.getX() < IPConstants.OCTET_MAX_B) {
			tempLocal = ip.getX() + 1;
			ip.setX(tempLocal);
			return ip;
		}

		else if (ip.getW() < IPConstants.OCTET_MAX_A) {
			tempLocal = ip.getW() + 1;
			ip.setW(tempLocal);
			return ip;
		}
		return ip;
	}

}
