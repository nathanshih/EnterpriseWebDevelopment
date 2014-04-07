/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week9;

import com.rbevans.bookingrate.BookingDay;
import com.rbevans.bookingrate.Rates;

/**
 * Use this object to calculate the rate locally.
 * @author Nathan Shih
 */
public class CalculateRateLocal {
	
	private Rates rate;
	private BookingDay bookingStart;
	private BookingDay bookingEnd;
	private int baseRate;
	private boolean isValidDates;
	private String details;

	public CalculateRateLocal() {
		rate = new Rates();
	}
	
	public BookingDay getBookingStart() {
		return bookingStart;
	}

	public void setBookingStart(BookingDay bookingStart) {
		this.bookingStart = bookingStart;
	}

	public BookingDay getBookingEnd() {
		return bookingEnd;
	}

	public void setBookingEnd(BookingDay bookingEnd) {
		this.bookingEnd = bookingEnd;
	}

	public int getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(int baseRate) {
		this.baseRate = baseRate;
	}

	public boolean isValidDates() {
		return isValidDates;
	}

	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isValidDates(BookingDay bookingStart, BookingDay bookingEnd) {
		rate.setBeginDate(bookingStart);
		rate.setEndDate(bookingEnd);
		isValidDates = rate.isValidDates();
		details = rate.getDetails();
		
		return isValidDates;
	}
	
	public String getRate(BookingDay bookingStart, BookingDay bookingEnd, int baseRate) {
		rate.setBeginDate(bookingStart);
		rate.setEndDate(bookingEnd);
		isValidDates = rate.isValidDates();
		
		if (isValidDates) {
			rate.setBaseRate(baseRate);
			return String.valueOf(rate.getCost());
		} else {
			details = rate.getDetails();
			return details;
		}
	}
}
