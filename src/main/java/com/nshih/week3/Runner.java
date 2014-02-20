package com.nshih.week3;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Nathan Shih
 */
public class Runner {

	public static void main(String[] args) {
		Integer product;
		Integer number1;
		Integer number2;
		String result;
		
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat();
		
		// Get first number
		System.out.println("Enter in a number");
		number1 = in.nextInt();
		System.out.println("First number is " + number1);
		
		// Get next number
		System.out.println("Enter in another number");
		number2 = in.nextInt();
		System.out.println("Second number is " + number2);
		
		// Call productOfTwoIntegers to get the product
		product = Week3Assignment.productOfTwoIntegers(number1, number2);
		
		// Check if product is negative
		if (product.intValue() < 0) {
			df.setNegativePrefix("(");
			df.setNegativeSuffix(")");
			result = df.format(product);
		} else {
			result = product.toString();
		}
		
		// Display the product to the console
		System.out.print("The product of " + number1 + " and " + number2 + " equals " + result + ".");
		
		in.close();
	}
}
