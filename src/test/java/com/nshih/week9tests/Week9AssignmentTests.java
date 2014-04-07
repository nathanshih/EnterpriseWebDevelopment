/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week9tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import com.nshih.utils.ClassServer;
import com.nshih.week9.CalculateRateLocal;
import com.rbevans.bookingrate.BookingDay;

/**
 * @author Nathan Shih
 */
public class Week9AssignmentTests {

	private static Socket clientSocket;
	private static PrintWriter out;
    private static BufferedReader in;
    private static CalculateRateLocal calculateRateLocal;
	
	@BeforeClass
	public static void setupClass() {
		try {
			clientSocket = new Socket("localhost", ClassServer.MY_PORT);
			// set up new output writer and input reader
		    out = new PrintWriter(clientSocket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
			
		calculateRateLocal = new CalculateRateLocal();
	}
	
	@AfterClass
	public static void tearDown() {
		try {
			clientSocket.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	/**
	 * Happy path test using good data.
	 */
	@Test
	public void testHappyPath() {
		String input = null;
		String output = null;
		
		input = "2008:7:1:2008:7:8:40";
		
		// expected values
		BookingDay expectedBookingStart = new BookingDay(2008, 7, 1);
		BookingDay expectedBookingEnd= new BookingDay(2008, 7, 8);
		String expectedRate = String.valueOf(calculateRateLocal.getRate(expectedBookingStart, expectedBookingEnd, 40));
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
	
	/**
	 * Send null input to server.
	 */
	@Test
	public void testNullInput() {
		String input = null;
		String output = null;
		
		// expected value
		String expectedRate = "-0.01";
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
	
	/**
	 * Bad input: "foo:bar:baz:bom"
	 */
	@Test
	public void testBadInput1() {
		String input = null;
		String output = null;
		
		input = "foo:bar:baz:bom";
		
		// expected value
		String expectedRate = "-0.01";
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
	
	/**
	 * Bad input: "1:1:1:1:1"
	 */
	@Test
	public void testBadInput2() {
		String input = null;
		String output = null;
		
		input = "1:1:1:1:1";
		
		// expected value
		String expectedRate = "-0.01";
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
	
	/**
	 * Invalid dates: end date is far in the past
	 */
	@Test
	public void testInvalidDates1() {
		String input = null;
		String output = null;
		
		input = "2020:7:1:1908:7:8:40";
		
		// expected values
		String expectedRate = "-0.01";
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
	
	/**
	 * Invalid dates: end date is a few days in the past
	 */
	@Test
	public void testInvalidDates2() {
		String input = null;
		String output = null;
		
		input = "2008:7:2:2008:7:1:40";
		
		// expected values
		String expectedRate = "-0.01";
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
	
	/**
	 * Base rate is less than 0
	 */
	@Test
	public void testNegativeBaseRate() {
		String input = null;
		String output = null;
		
		input = "2008:7:2:2008:7:1:-1";
		
		// expected values
		String expectedRate = "-0.01";
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
	
	/**
	 * Base rate is 0
	 */
	@Test
	public void testZeroBaseRate() {
		String input = null;
		String output = null;
		
		input = "2008:7:2:2008:7:1:0";
		
		// expected values
		String expectedRate = "-0.01";
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
	
	/**
	 * Base rate is greater than 100
	 */
	@Test
	public void test101BaseRate() {
		String input = null;
		String output = null;
		
		input = "2008:7:2:2008:7:1:101";
		
		// expected values
		String expectedRate = "-0.01";
		
		// send to server
		out.println(input);
		
		try {
			output = in.readLine();
			String[] actuals = output.split(":");
			Assert.assertEquals(expectedRate, actuals[0]);
		} catch (IOException ie) {
			Assert.fail(ie.getMessage());
		}
	}
}
