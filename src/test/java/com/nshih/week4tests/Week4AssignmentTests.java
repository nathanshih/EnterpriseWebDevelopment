/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nshih.week4.Contact;
import com.nshih.week4.Destroyer;
import com.nshih.week4.Ship;
import com.nshih.week4.Submarine;

/**
 * @author Nathan Shih
 */
public class Week4AssignmentTests {

	private static ArrayList<Contact> contacts;
	private static ArrayList<Ship> ships;
	private static ArrayList<Destroyer> destroyers;
	private static ArrayList<Submarine> submarines;
	
	@BeforeClass
	public static void setupClass() {
		contacts = new ArrayList<Contact>();
		ships = new ArrayList<Ship>();
		destroyers = new ArrayList<Destroyer>();
		submarines = new ArrayList<Submarine>();
	}
	
	@Test
	public void testCreate2Destroyers() throws Exception {
		destroyers.add(new Destroyer(509, 28, "USS Bainbridge", "Bainbridge-class", 1000));
		destroyers.add(new Destroyer(600, 30, "Lyndon B. Johnson", "Zumwalt-class", 5000));
	}
	
	@Test
	public void testCreate2Submarines() throws Exception {
		
	}
	
	@Test
	public void testCreate2P3s() throws Exception {
		
	}
}
