/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.rbevans.bookingrate.BookingDay;

/**
 * @author Nathan Shih
 */
public class RateServerThread implements Runnable {

	private final Socket socket;
	private CalculateRateLocal calculateRateLocal;
	private BookingDay bookingStart;
	private BookingDay bookingEnd;
	private int baseRate;
	private String reason;
	
	// input string must match this format (ex: 2008:7:1:2008:7:8:40)
	private final String format = "^\\d{4}(:)\\d{1,2}(:)\\d{1,2}(:)\\d{4}(:)\\d{1,2}(:)\\d{1,2}(:)\\d{2}$";
	
	public RateServerThread(Socket clientSocket) {
		this.socket = clientSocket;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		BufferedReader in = null;
        PrintWriter out = null;
		calculateRateLocal = new CalculateRateLocal();
		reason = "-0.01";
		
        try {
        	out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // do stuff with the in and out
            String input = null;
            String output = null;
            while (!socket.isClosed()) {
            	input = in.readLine();
            	
            	// check if input is null
            	if (input == null) {
            		reason = reason + ":Input cannot be null";
            		out.println(reason);
            		break;
            	}
            	
            	// validate if input is in correct format
            	if (!input.matches(format)) {
            		reason = reason + ":Input string must match this format"
        					+ " \"begin_year:begin_month:begin_day:end_year:end_month:end_day:base_rate\" "
        					+ "(e.g: 2008:7:1:2008:7:8:40)";
            		out.println(reason);
            		break;
            	}
            	
            	// attempt to parse data returns false if data is invalid
            	if (!parseData(input)) {
            		out.println(reason);
            		break;
            		
            	// data is valid return rate
            	} else {
            		output = String.valueOf(calculateRateLocal.getRate(bookingStart, bookingEnd, baseRate));
            		output = output + ":Quoted Rate";
            		out.println(output);
            	}
            }
        } catch (IOException ie) {
        	ie.printStackTrace();
        } finally {
        	try {
        		if (out != null) {
        			out.close();
        		}
        		if (in != null) {
        			in.close();
        		}
        		if (socket != null) {
        			socket.close();
        		}
        	} catch (IOException ie) {
        		ie.printStackTrace();
        	}
        }
	}
	
	/**
	 * This method attempts to parse the data while checking each element for its validity. Any
	 * invalid elements will result in a return value of false along with which elements are invalid.
	 * @param input The input string from the client to be parsed.
	 * @return Returns true if data is valid otherwise returns false.
	 */
	private boolean parseData(String input) {
		boolean isParsed = false;
		
		String[] data = input.split(":");
		
		// get start date
		int startYear = Integer.valueOf(data[0]);
		int startMonth = Integer.valueOf(data[1]);
		int startDay = Integer.valueOf(data[2]);
		bookingStart = new BookingDay(startYear, startMonth, startDay);
		
		// get end date
		int endYear = Integer.valueOf(data[3]);
		int endMonth = Integer.valueOf(data[4]);
		int endDay = Integer.valueOf(data[5]);
		bookingEnd = new BookingDay(endYear, endMonth, endDay);
		
		// get base rate
		baseRate = Integer.valueOf(data[6]);
		
		if (!bookingStart.isValidDate()) {
			reason = reason + ":Start date is not a valid date";
		} 
		if (!bookingEnd.isValidDate()) {
			reason = reason + ":End date is not a valid date";
		} 
		if (baseRate < 0 || baseRate > 100) {
			reason = reason + ":Base rate needs to be greater than 0 and less than 100";
		} else {
			reason = "";
			isParsed = true;
		}
		
		return isParsed;
	}
}
