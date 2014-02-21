/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4;

/**
 * @author Nathan Shih
 */
public class Submarine extends Ship {

	private int numberTorpedoes;

	public Submarine(int length, int speed, String name, String type, int numberTorpedos) throws Exception {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
		setNumberTorpedoes(numberTorpedos);
	}
	
	public int getNumberTorpedoes() {
		return numberTorpedoes;
	}

	public void setNumberTorpedoes(int numberTorpedoes) throws Exception {
		if (numberTorpedoes < 0) {
			throw new Exception("Attribute 'numberTorpedoes' must be greater than -1.");
		} else {
			this.numberTorpedoes = numberTorpedoes;
		}
	}
	
	public void setNumberTorpedoes(String numberTorpedoes) throws Exception {
		try {
			setNumberTorpedoes(Integer.parseInt(numberTorpedoes));
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.getLocalizedMessage());
			System.err.println("Setting 'numberTorpedoes' to 2.");
			setNumberTorpedoes(2); // per step 4, set to 2
		}
	}
	
	public String toString() {
		return "New contact! Type: " + getType() + ", Name: " + getName() + ", Length: " + getLength() + 
				" ft, Speed: " + getSpeed() + " knots, Number of torpedoes: " + getNumberTorpedoes();
	}
}
