/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week6;

import java.util.Calendar;

import com.nshih.utils.Hikes;
import com.rbevans.bookingrate.BookingDay;
import com.rbevans.bookingrate.Rates;

/**
 * Use this object to calculate the rate locally.
 * @author Nathan Shih
 */
public class CalculateRateFromLocal implements CalculateRate {
	
	private Rates rate;

	public CalculateRateFromLocal() {
		rate = new Rates();
	}
	
	@Override
	public double getRate(BookingDay bookingStart, Integer duration, String selectedHike) {
		// calculate the end date based upon duration
		Calendar endDate = Calendar.getInstance();
		endDate.set(bookingStart.getYear(), bookingStart.getMonth(), bookingStart.getDayOfMonth());
		endDate.add(Calendar.DATE, duration);
		BookingDay bookingEnd = new BookingDay(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
			
		if (bookingEnd.isValidDate()) {
			rate.setBeginDate(bookingStart);
			rate.setEndDate(bookingEnd);

			// set base rates on chosen hike
			switch (selectedHike) {
    			case Hikes.GARDINER_LAKE:
    				rate.setBaseRate(Hikes.GARDINER_LAKE_RATE);
    				break;
    			case Hikes.HELLROARING_PLATEAU:
    				rate.setBaseRate(Hikes.HELLROARING_PLATEAU_RATE);
    				break;
    			case Hikes.BEATEN_PATH:
    				rate.setBaseRate(Hikes.BEATEN_PATH_RATE);
    				break;						
			}
			return rate.getCost();
		} else {
			return 0;
		}
	}

	@Override
	public String getDetails() {
		return rate.getDetails();
	}
}
