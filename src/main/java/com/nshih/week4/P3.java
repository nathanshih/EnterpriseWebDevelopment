/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4;

/**
 * @author Nathan Shih
 */
public class P3 extends Aircraft {

	private int numberEngines;

	public P3 (int length, int speed, String name, String type, int numberEngines) throws Exception {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
		setNumberEngines(numberEngines);
	}
	public int getNumberEngines() {
		return numberEngines;
	}

	public void setNumberEngines(int numberEngines) throws Exception {
		if (numberEngines < 1) {
			throw new Exception("Attribute 'numberEngines' must be greater than 0.");
		} else {
			this.numberEngines = numberEngines;
		}
	}
	
	public String toString() {
		return "New contact, type: " + this.getType() + " name: " + this.getName();
	}
}
