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

/**
 * @author Nathan Shih
 */
public class Week9AssignmentTests {

	private static Socket clientSocket;
	private static PrintWriter out;
    private static BufferedReader in;
	
	@BeforeClass
	public static void setupClass() {
		try {
			clientSocket = new Socket("localhost", 1116);
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
	
	@Test
	public void testHappyPath() {
		String input = null;
		String output = null;
		
		input = "2008:7:1:2008:7:8:40";
		out.println(input);
		
		try {
			output = in.readLine();
			System.out.println(output);
		} catch (IOException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
	}
}
