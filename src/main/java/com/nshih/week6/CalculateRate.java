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
	 * Calculates the rate based upon the start date, length of hike, and base rate.
	 * @param startDay the start date of the hike 
	 * @param duration length of the hike
	 * @param selectedHike base rate of the hike
	 * @return The calculated rate as a double.
	 */
	public double getRate(BookingDay startDay, Integer duration, String selectedHike);
}
