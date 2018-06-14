package com.ip.Main;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.ip.service.AllocationService;
import com.ip.service.HeartBeatService;
import com.ip.service.IPServiceMapper;
/**
 * MainClass.class is entry point of Application
 * main method triggering both services : allocate  and refresh
 * @author NEERAJ SWAMI
 *
 */
public class MainClass {
	
	 
	 public static void main(String[] args) {
		 AllocationService allocateIP = new IPServiceMapper();
		 Thread t = new Thread(allocateIP);
		 t.start();
		/*  Start Input MAC for IP allocation */	
		 String mac = "00-80-C8-E3-4C-BD";
		 String mac2 = "00-90-C8-E3-4C-BD";
		 String mac3 = "00-80-C1-E3-4C-BD";
		 String mac4 = "00-80-C8-E2-4C-BD";
		 String mac5 = "00-80-C8-E3-6C-BD";
		 String mac6 = "00-90-C8-E5-4C-BD";
		 String mac7 = "00-85-C5-E3-4C-BD";
		 String mac8 = "00-80-C8-E4-3C-BD";
		 String mac9 = "00-80-C8-E3-4C-AD";
		 String mac10 = "00-85-C8-E3-4C-AD";
		 String mac11 = "00-80-A8-B3-4C-BD";
		 String mac12 = "00-80-C0-E0-1C-BD";
		 
		 String mac19 = "No IP Allocation";
		 /*  End Input MAC for IP allocation */
		 
		System.out.println("IP for mac  -->" +  allocateIP.allocate(mac));
		System.out.println("IP for mac2 -->" +  allocateIP.allocate(mac2)); 
		System.out.println("IP for mac3 --> " +  allocateIP.allocate(mac3));
		System.out.println("IP for mac4 -->" +  allocateIP.allocate(mac4));
		System.out.println("IP for mac5 -->" +  allocateIP.allocate(mac5));
		System.out.println("IP for mac6 -->" +  allocateIP.allocate(mac6)); 
		System.out.println("IP for mac7 -->" +  allocateIP.allocate(mac7));
		System.out.println("IP for mac8 -->" +  allocateIP.allocate(mac8));
		System.out.println("IP for mac9 -->" +  allocateIP.allocate(mac9)); 
		System.out.println("IP for mac10 -->" +  allocateIP.allocate(mac10)); 
		System.out.println("IP for mac11 -->" +  allocateIP.allocate(mac11)); 
		System.out.println("IP for mac12 -->" +  allocateIP.allocate(mac12)); 
		
		
		HeartBeatService heatbeatService = new IPServiceMapper();
		/*Start input to refresh expiry time for given Map*/
		heatbeatService.refresh(mac19,"10.11.12.12");
		heatbeatService.refresh(mac,"10.11.12.124");
		/*End input to refresh expiry time for given Map*/
		
		 
	}
	

}
