/*
 * Author : Thiracy Mary
 */

package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.ftb.model.Booking;
import com.capg.ftb.model.ScheduledFlight;

public interface IFlightBookingService {
	
	public Booking addBooking(Booking booking);
	
	public Booking modifyBooking(Booking booking,BigInteger bookingId);
	 
	public Booking viewBooking(BigInteger bookingId);
	
	public List<Booking> viewAllBookings();
	
	public Booking deleteBooking(BigInteger bookingId);
	
	//public Booking validateBooking(BigInteger boookingId);
	
	public Booking getById(BigInteger bookingId);
	
	public List<ScheduledFlight> searchForScheduledFlight(String srcAirport,String dstnAirport,String deptDate);

	void modifyScheduledFlight(BigInteger scheduledFlightId, int passengersCount);
}
