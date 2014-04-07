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
	private final String format = "^\\d{4}(:)\\d{1,2}(:)\\d{1,2}(:)\\d{4}(:)\\d{1,2}(:)\\d{1,2}(:)\\d{1,2}$";
	
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
			
        try {
        	out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // do stuff with the in and out
            String input = null;
            while (!socket.isClosed()) {
            	reason = "-0.01";
            	input = in.readLine();
            	
            	// check if input is null
            	if (input == null) {
            		reason = reason + ":Input cannot be null";
            		out.println(reason);
            		break;
            	
            	// validate if input is in correct format
            	} else if (!input.matches(format)) {
            		reason = reason + ":Input string must match this format"
        					+ " \"begin_year:begin_month:begin_day:end_year:end_month:end_day:base_rate\" "
        					+ "(e.g: 2008:7:1:2008:7:8:40)";
            		out.println(reason);
            	
            	// attempt to parse data returns false if data is invalid
            	} else if (!parseData(input)) {
            		out.println(reason);
            		
            	// data is valid return rate
            	} else {
            		reason = calculateRateLocal.getRate(bookingStart, bookingEnd, baseRate);
            		reason = reason + ":Quoted Rate";
            		out.println(reason);
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
		boolean isDatesValid = false;
		boolean baseRateValid = false;
		
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
		
		if (!calculateRateLocal.isValidDates(bookingStart, bookingEnd)) {
			reason = reason + ":" + calculateRateLocal.getDetails();
		} else {
			isDatesValid = true;
		}
		
		if (baseRate < 1 || baseRate > 99) {
			reason = reason + ":Base rate needs to be greater than 0 and less than 100";
		} else {
			baseRateValid = true;
		}
		
		if (isDatesValid && baseRateValid) {
			return true;
		} else {
			return false;
		}
	}
}
