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
			// TODO Auto-generated catch block
			uhe.printStackTrace();
		} catch (IOException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
			
		calculateRateLocal = new CalculateRateLocal();
	}
	
	@AfterClass
	public static void tearDown() {
		try {
			clientSocket.close();
		} catch (IOException ie) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
	}
	
	/**
	 * Send null input to server.
	 */
	
	/**
	 * Bad input: "foo:bar:baz:bom"
	 */
	
	/**
	 * Bad input: "1:1:1:1:1"
	 */
}
