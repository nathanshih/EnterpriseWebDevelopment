package com.nshih.week3tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nshih.week3.Week3Assignment;

public class Week3AssignmentTests {
	
	private int product;
	
	// Set product back to 0 prior to each test run
	@Before
	public void setup() {
		product = 0;
	}
	
	// 2 * 2 = 4
	@Test
	public void testSimple() {
		product = Week3Assignment.productOfTwoIntegers(2, 2);
		Assert.assertEquals(4, product);
	}
	
	// -1 * 15 = -15
	@Test
	public void testNegative() {
		product = Week3Assignment.productOfTwoIntegers(-1, 15);
		Assert.assertEquals(-15, product);
	}
	
	// 0 * 234 = 0
	@Test
	public void testZero() {
		product = Week3Assignment.productOfTwoIntegers(0, 234);
		Assert.assertEquals(0, product);
	}
	
	// 123 * 321 = 39483
	@Test
	public void testLarge() {
		product = Week3Assignment.productOfTwoIntegers(123, 321);
		Assert.assertEquals(39483, product);
	}
}
