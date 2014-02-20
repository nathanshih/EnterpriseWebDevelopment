package com.nshih.week3;

/**
 * @author Nathan Shih
 */
public class Week3Assignment {
	
	public static int productOfTwoIntegers(int a, int b) {
		
		int product = 0;
		
		try {
			product = a * b;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return product;
	}
}
