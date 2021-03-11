/*
 * Author : Thiracy Mary
 */

package com.capg.ftb.service;

import java.util.List;

import com.capg.ftb.model.Booking;
import com.capg.ftb.model.ScheduledFlight;

public interface IFlightBookingService {
	
	public Booking addBooking(Booking booking);
	
	public Booking modifyBooking(int bookingId);
	 
	public Booking viewBooking(int bookingId);
	
	public List<Booking> viewAllBookings();
	
	public Booking deleteBooking(int bookingId);
	
	public Booking validateBooking(int boookingId);
	
	public Booking getById(int bookingId);
	
	public List<ScheduledFlight> searchForScheduledFlight(String srcAirport,String dstnAirport,String deptDate);

}
