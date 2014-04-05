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

	public CalculateRateLocal() {
		rate = new Rates();
	}
	
	public double getRate(BookingDay bookingStart, BookingDay bookingEnd, int baseRate) {

		if (bookingStart.isValidDate() && bookingEnd.isValidDate()) {
			rate.setBeginDate(bookingStart);
			rate.setEndDate(bookingEnd);
    		rate.setBaseRate(baseRate);

			return rate.getCost();
		} else {
			return 0;
		}
	}

	public String getDetails() {
		return rate.getDetails();
	}
}
