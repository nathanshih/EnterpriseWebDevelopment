/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4;

/**
 * @author Nathan Shih
 */
public class Submarine extends Ship {

	private int numberTorpedos;

	public Submarine(int length, int speed, String name, String type, int numberTorpedos) throws Exception {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
		setNumberTorpedos(numberTorpedos);
	}
	
	public int getNumberTorpedos() {
		return numberTorpedos;
	}

	public void setNumberTorpedos(int numberTorpedos) throws Exception {
		if (numberTorpedos < 0) {
			throw new Exception("Attribute 'numberTorpedos' must be greater than -1.");
		} else {
			this.numberTorpedos = numberTorpedos;
		}
	}
	
	public void setNumberTorpedos(String numberTorpedos) throws Exception {
		try {
			setNumberTorpedos(Integer.parseInt(numberTorpedos));
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.getLocalizedMessage());
			System.err.println("Setting numberTorpedos to 2.");
			this.numberTorpedos = 2;
		}
	}
	
	public String toString() {
		return "New contact! Type: " + getType() + ", Name: " + getName() + ", Length: " + getLength() + 
				" ft, Speed: " + getSpeed() + " knots, Number of torpedoes: " + getNumberTorpedos();
	}
}
