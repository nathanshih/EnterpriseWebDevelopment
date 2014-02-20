/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4;

/**
 * @author Nathan Shih
 */
public class Destroyer extends Ship {

	private int numberMissle;

	public Destroyer(int length, int speed, String name, String type, int numberMissle) throws Exception {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
		setNumberMissle(numberMissle);
	}
	
	public int getNumberMissle() {
		return numberMissle;
	}
	
	public void setNumberMissle(int numberMissle) throws Exception {
		if (numberMissle < 0) {
			throw new Exception("Attribute 'numberMissle' must be greater than -1.");
		} else {
			this.numberMissle = numberMissle;
		}
	}
	
	public void setNumberMissle(String numberMissle) throws Exception {
		try {
			setNumberMissle(Integer.parseInt(numberMissle));
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.getLocalizedMessage());
			System.err.println("Setting numberMissle to 2.");
			this.numberMissle = 2;
		}
	}

	public String toString() {
		return "New contact! Type: " + getType() + ", Name: " + getName() + ", Length: " + getLength() + 
				"ft, Speed: " + getSpeed() + "knots, Number of missiles: " + getNumberMissle();
	}
}
