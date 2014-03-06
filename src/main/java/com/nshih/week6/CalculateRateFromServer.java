/** Copyright © 2014 
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

import com.nshih.utils.Hikes;
import com.rbevans.bookingrate.BookingDay;

/**
 * Use this object to calculate the rate from the server.
 * @author Nathan Shih
 */
public class CalculateRateFromServer implements CalculateRate {

	private Socket rateSocket;
	private PrintWriter out;
    private BufferedReader in;
    private String serverUrl;
    private int port;
    private String details;
    
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
	public double getRate(BookingDay bookingStart, Integer duration, String selectedHike) {
		String input = null;
		String output = null;
		double rate = 0;
		
		// build the input string for the rate server
		switch (selectedHike) {
			case Hikes.GARDINER_LAKE:
				input = "0";
				break;
			case Hikes.HELLROARING_PLATEAU:
				input = "1";
				break;
			case Hikes.BEATEN_PATH:
				input = "2";
				break;						
		}
		input = input + ":" + bookingStart.getYear() + ":" + bookingStart.getMonth() + ":" + bookingStart.getDayOfMonth()
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
				details = results[1];
			}
			rateSocket.close();
			return rate;
		} catch (IOException e1) {
			e1.printStackTrace();
			return rate;
		}
	}

	@Override
	public String getDetails() {
		return details;
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
