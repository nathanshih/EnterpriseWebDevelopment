/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4;

/**
 * @author Nathan Shih
 */
public class Destroyer extends Ship {

	private int numberMissiles;

	public Destroyer(int length, int speed, String name, String type, int numberMissile) throws Exception {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
		setNumberMissiles(numberMissile);
	}
	
	public int getNumberMissiles() {
		return numberMissiles;
	}
	
	public void setNumberMissiles(int numberMissiles) throws Exception {
		if (numberMissiles < 0) {
			throw new Exception("Attribute 'numberMissiles' must be greater than -1.");
		} else {
			this.numberMissiles = numberMissiles;
		}
	}
	
	public void setNumberMissiles(String numberMissiles) throws Exception {
		try {
			setNumberMissiles(Integer.parseInt(numberMissiles));
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.getLocalizedMessage());
			System.err.println("Setting 'numberMissiles' to 2.");
			setNumberMissiles(2); // per step 3, set to 2
		}
	}

	public String toString() {
		return "New contact! Type: " + getType() + ", Name: " + getName() + ", Length: " + getLength() + 
				" ft, Speed: " + getSpeed() + " knots, Number of missiles: " + getNumberMissiles();
	}
}
