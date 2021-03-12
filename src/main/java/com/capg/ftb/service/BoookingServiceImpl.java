/*
 * Author : Thiracy Mary
 */

package com.capg.ftb.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.IBookingDAO;
import com.capg.ftb.dao.IScheduleFlightDAO;
import com.capg.ftb.dao.IUsersDAO;
import com.capg.ftb.exception.AirportNotFoundException;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.exception.SeatsNotAvailableException;
import com.capg.ftb.exception.UserNotFoundException;
import com.capg.ftb.model.Booking;
import com.capg.ftb.model.ScheduledFlight;
import com.capg.ftb.model.Users;

@Service
public class BoookingServiceImpl implements IFlightBookingService{

	@Autowired
	private IBookingDAO bookingDao;
	
	@Autowired
	private IScheduleFlightDAO scheduleFilghtDao;
	
	@Autowired 
	IUsersDAO usersDao;
	
	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		
	
		Optional<ScheduledFlight> optional1=scheduleFilghtDao.findById(booking.getScheduledFlightId());
		ScheduledFlight scheduledFlight=(ScheduledFlight)optional1.orElseThrow(() ->new FlightNotFoundException("Scheduled Flight Not Found with the given ID : "+booking.getScheduledFlightId()));
		
		
		if(booking.getNoOfPassangers()>scheduledFlight.getAvailableSeats())
		{
			throw new SeatsNotAvailableException("Passengers are more than the available seats");
		}
		else
		{
			LocalDate date = LocalDate.now();
			booking.setBookingDate(date);
			
			booking.setTicketCost(scheduledFlight.getCostPerHead()*booking.getNoOfPassangers());
			
			modifyScheduledFlight(scheduledFlight.getScheduleFlightId(),booking.getNoOfPassangers());
			
			Booking booking1=bookingDao.save(booking);
			return booking1;
		}
		
		
	}

	@Override
	public Booking modifyBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking viewBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> viewAllBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking deleteBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking validateBooking(BigInteger boookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking getById(BigInteger bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduledFlight> searchForScheduledFlight(String srcAirport, String dstnAirport, String deptDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void modifyScheduledFlight(BigInteger scheduledFlightId,int passengersCount) {
		// TODO Auto-generated method stub
		
		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduledFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduledFlightId));
		sFlight.setAvailableSeats(sFlight.getAvailableSeats()-passengersCount);
		ScheduledFlight sFlight2=sFlight;
		scheduleFilghtDao.deleteById(sFlight.getScheduleFlightId());
		scheduleFilghtDao.save(sFlight2);
		
	}

}
