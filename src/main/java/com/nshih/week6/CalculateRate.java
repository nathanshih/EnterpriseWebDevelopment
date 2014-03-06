/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week6;

import com.rbevans.bookingrate.BookingDay;

/**
 * @author Nathan Shih
 */
public interface CalculateRate {

	/**
	 * Calculates the rate based upon the start date, length of hike, and selected hike.
	 * @param bookingStart the start date of the hike 
	 * @param duration length of the hike
	 * @param selectedHike the selected hike
	 * @return The calculated rate as a double.
	 */
	public double getRate(BookingDay bookingStart, Integer duration, String selectedHike);
	
	/**
	 * In case of an error, get the details of the error.
	 * @return The details of the error.
	 */
	public String getDetails();
}
