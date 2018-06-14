package com.ip.service;

/**
 * AClassIP.java this class reflects one of the FOUR IP allocation classes
 * POJO class attributes are referring to Octet of any IP
 * @author NEERAJ SWAMI
 *
 */
public class AClassIP {

	private int w;
	private int x ;
	private int y;
	private int z;
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	
	public String toString()
	{
		return w + ":" + x + ":" + y +  ":"  + z;
	}

}
