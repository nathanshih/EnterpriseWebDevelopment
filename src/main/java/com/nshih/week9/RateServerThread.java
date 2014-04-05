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
		reason = null;
		
        try {
        	out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // do stuff with the in and out
            String input = null;
            String output = null;
            while (!socket.isClosed()) {
            	// validate the input
            	input = in.readLine();
            	if (input == null) {
            		reason = "-0.01:Input cannot be null";
            		out.println(reason);
            		break;
            	} else if (!validateInput(input)) {
            		out.println(reason);
            		break;
            	} else {
            		if (parseData(input)) {
            			output = String.valueOf(calculateRateLocal.getRate(bookingStart, bookingEnd, baseRate));
            			output = output + ":Quoted Rate";
            			out.println(output);
            		} else {
            			break;
            		}
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
	
	private boolean validateInput(String input) {
		boolean isValid = false;
		
		if (input.matches(format)) {
			isValid = true;
		} else {
			reason = "-0.01:Input string must match this format: "
					+ "begin_year:begin_month:begin_day:end_year:end_month:end_day:base_rate "
					+ "(e.g: 2008:7:1:2008:7:8:40)";
		}
		
		return isValid;
	}
	
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
		
		if (bookingStart.isValidDate() && bookingEnd.isValidDate() && (baseRate > 0)) {
			isParsed = true;
		}
		
		return isParsed;
	}
}
