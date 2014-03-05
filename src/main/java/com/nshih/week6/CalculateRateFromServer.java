/** Copyright � 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.rbevans.bookingrate.BookingDay;

/**
 * Use this object to calculate the rate from the server.
 * @author Nathan Shih
 */
public class CalculateRateFromServer implements CalculateRate {

	private static Socket rateSocket;
	private static PrintWriter out;
    private static BufferedReader in;
    
    private String serverUrl;
    private int port;
	
    // constants
    private static final String GARDINER_LAKE = "Gardiner Lake";
	private static final String HELLROARING_PLATEAU = "Hellroaring Plateau";
	private static final String BEATEN_PATH = "Beaten Path";
    
	public CalculateRateFromServer(String serverUrl, int port) {		
		try {
			// attempt to connect to server
			connectToRateServer(serverUrl, port);
			
			this.serverUrl = serverUrl;
		    this.port = port;
		} catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverUrl);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + serverUrl);
            System.exit(1);
        }
	}
	
	@Override
	public double getRate(BookingDay startDay, Integer duration, String selectedHike) {
		String input = null;
		String output = null;
		double rate = 0;
		
		// build the input string for the rate server
		switch (selectedHike) {
			case GARDINER_LAKE:
				input = "0";
				break;
			case HELLROARING_PLATEAU:
				input = "1";
				break;
			case BEATEN_PATH:
				input = "2";
				break;						
		}
		input = input + ":" + startDay.getYear() + ":" + startDay.getMonth() + ":" + startDay.getDayOfMonth()
					+ ":" + duration;
		
		// establish a new connection to the rate server
		if (rateSocket.isClosed()) {
			try {
				connectToRateServer(serverUrl, port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// send the output string
		out.println(input);
		
		// process the return string
		try {
			output = in.readLine();
			if (output != null) {
				String[] results = output.split(":");
				rate = Double.parseDouble(results[0]);
			}
			rateSocket.close();
			return rate;
		} catch (IOException e1) {
			e1.printStackTrace();
			return rate;
		}
	}
	
	private void connectToRateServer(String serverUrl, int port) throws IOException {
		System.out.println("Connecting to " + serverUrl + " on port " + port);
		rateSocket = new Socket(serverUrl, port);
		rateSocket.setSoTimeout(10000);
	    System.out.println("Connected.");

	    // set up new output writer and input reader
	    out = new PrintWriter(rateSocket.getOutputStream(), true);
	    in = new BufferedReader(new InputStreamReader(rateSocket.getInputStream()));
	}
}
