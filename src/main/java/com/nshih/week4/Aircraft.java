/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4;

/**
 * @author Nathan Shih
 */
public abstract class Aircraft implements Contact {

	private int length;
	private int speed;
	private int altitude;
	private String name;
	private String type;
	
	@Override
	public int getLength() {
		return length;
	}

	@Override
	public void setLength(int length) throws Exception {
		if (length < 1) {
			throw new Exception("Attribute 'length' must be greater than 0.");
		} else {
			this.length = length;
		}
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(int speed) throws Exception {
		if (speed < 0) {
			throw new Exception("Attribute 'speed' must be greater than -1.");
		} else {
			this.speed = speed;
		}
	}

	@Override
	public void setSpeed(String speed) throws Exception {
		try {
			setSpeed(Integer.parseInt(speed));
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.getMessage());
		}
	}
	
	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) throws Exception {
		if (altitude < 0) {
			throw new Exception("Altitude must be greater than -1.");
		} else {
			this.altitude = altitude;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}
}
