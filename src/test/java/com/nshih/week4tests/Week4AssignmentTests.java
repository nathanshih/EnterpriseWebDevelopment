/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nshih.week4.Contact;
import com.nshih.week4.Destroyer;
import com.nshih.week4.P3;
import com.nshih.week4.Ship;
import com.nshih.week4.Submarine;

/**
 * @author Nathan Shih
 */
public class Week4AssignmentTests {

	private static Map<String, Contact> contacts;
	private static Map<String, Ship> ships;
	private static Map<String, Destroyer> destroyers;
	private static Map<String, Submarine> submarines;
	
	@BeforeClass
	public static void setupClass() {
		contacts = new HashMap<String, Contact>();
		ships = new HashMap<String, Ship>();
		destroyers = new HashMap<String, Destroyer>();
		submarines = new HashMap<String, Submarine>();
	}
	
	/**
	 * Create 2 destroyers using good input.
	 * @throws Exception
	 */
	@Test
	public void testCreate2Destroyers() throws Exception {
		Destroyer ussBainbridge = new Destroyer(509, 28, "USS Bainbridge", "Bainbridge-class", 1000);
		Destroyer lyndonBJohnson = new Destroyer(600, 30, "Lyndon B. Johnson", "Zumwalt-class", 5000);

		// add destroyers to destroyers collection and verify
		destroyers.put(ussBainbridge.getName(), ussBainbridge);
		destroyers.put(lyndonBJohnson.getName(), lyndonBJohnson);
		Assert.assertEquals(ussBainbridge, destroyers.get(ussBainbridge.getName()));
		Assert.assertEquals(lyndonBJohnson, destroyers.get(lyndonBJohnson.getName()));
		
		// add destroyers to ships collection and verify
		ships.put(ussBainbridge.getName(), ussBainbridge);
		ships.put(lyndonBJohnson.getName(), lyndonBJohnson);
		Assert.assertEquals(ussBainbridge, ships.get(ussBainbridge.getName()));
		Assert.assertEquals(lyndonBJohnson, ships.get(lyndonBJohnson.getName()));
		
		// add destroyers to contacts collection and verify
		contacts.put(ussBainbridge.getName(), ussBainbridge);
		contacts.put(lyndonBJohnson.getName(), lyndonBJohnson);
		Assert.assertEquals(ussBainbridge, contacts.get(ussBainbridge.getName()));
		Assert.assertEquals(lyndonBJohnson, contacts.get(lyndonBJohnson.getName()));
	}
	
	/**
	 * Create 2 submarines using good input.
	 * @throws Exception
	 */
	@Test
	public void testCreate2Submarines() throws Exception {
		Submarine ussWashington = new Submarine(377, 25, "USS Washington", "Virginia-class", 250);
		Submarine chicago = new Submarine(362, 20, "Chicago", "Los Angeles-class", 200);
		
		// add submarines to submarines collection and verify
		submarines.put(ussWashington.getName(), ussWashington);
		submarines.put(chicago.getName(), chicago);
		Assert.assertEquals(ussWashington, submarines.get(ussWashington.getName()));
		Assert.assertEquals(chicago, submarines.get(chicago.getName()));
		
		// add submarines to ships collection and verify
		ships.put(ussWashington.getName(), ussWashington);
		ships.put(chicago.getName(), chicago);
		Assert.assertEquals(ussWashington, ships.get(ussWashington.getName()));
		Assert.assertEquals(chicago, ships.get(chicago.getName()));
		
		// add submarines to contacts collection and verify
		contacts.put(ussWashington.getName(), ussWashington);
		contacts.put(chicago.getName(), chicago);
		Assert.assertEquals(ussWashington, contacts.get(ussWashington.getName()));
		Assert.assertEquals(chicago, contacts.get(chicago.getName()));
	}
	
	/**
	 * Create 2 P3s using good input.
	 * @throws Exception
	 */
	@Test
	public void testCreate2P3s() throws Exception {
		P3 orion = new P3(116, 405, "Orion", "AP-3C", 4);
		P3 aurora = new P3(116, 380, "Aurora", "CP-140", 4);
		
		// add P3s to contacts collection and verify
		contacts.put(orion.getName(), orion);
		contacts.put(aurora.getName(), aurora);
		Assert.assertEquals(orion, contacts.get(orion.getName()));
		Assert.assertEquals(aurora, contacts.get(aurora.getName()));
	}
}
