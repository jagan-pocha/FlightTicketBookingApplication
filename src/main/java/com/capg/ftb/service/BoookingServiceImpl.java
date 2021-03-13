/*
 * Author : Thiracy Mary
 */

package com.capg.ftb.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.IBookingDAO;
import com.capg.ftb.dao.IScheduleFlightDAO;
import com.capg.ftb.dao.IUsersDAO;
import com.capg.ftb.exception.AirportNotFoundException;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.exception.RecordNotFoundException;
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
	@Autowired
	private ScheduledFlightServiceImpl scheduledFService;
	
	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		
		Optional<ScheduledFlight> optional1=scheduleFilghtDao.findById(booking.getScheduledFlightId());
		ScheduledFlight scheduledFlight=(ScheduledFlight)optional1.orElseThrow(() ->new FlightNotFoundException("Scheduled Flight Not Found with the given ID : "+booking.getScheduledFlightId()));
		
		
		if(booking.getNoOfPassangers()>scheduledFlight.getAvailableSeats())
		{
			throw new SeatsNotAvailableException("Passengers are more than the available seats");
		}
		else if(!(booking.getNoOfPassangers()==booking.getPassengerList().size()))
		{
			throw new SeatsNotAvailableException("Passengers List is more than no of passengers");
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
	public Booking modifyBooking(Booking booking,BigInteger bookingId)
	{
		Booking booking1=validateBooking(bookingId);
		if(booking1==null)
		{
			return null;
		}
		else
		{
			Booking booking2=bookingDao.save(booking);
			return booking2;
		}	
	}	
	

	@Override
	public Booking viewBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		Optional<Booking> optional=bookingDao.findById(bookingId);
		Booking vbooking=optional.orElseThrow(()->new RecordNotFoundException("Booking Not Existed with the id : "+bookingId));
		return vbooking;
	}

	@Override
	public List<Booking> viewAllBookings() {
		// TODO Auto-generated method stub
		List<Booking> bookingList=(List<Booking>) bookingDao.findAll();
		return bookingList;
	}
	


	@Override
	public Booking deleteBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		Booking booking2=validateBooking(bookingId);
		
		Optional<ScheduledFlight> optional1=scheduleFilghtDao.findById(booking2.getScheduledFlightId());
		ScheduledFlight scheduledFlight=(ScheduledFlight)optional1.orElseThrow(() ->new FlightNotFoundException("Scheduled Flight Not Found with the given ID : "+booking2.getScheduledFlightId()));
		
		if(booking2==null)
		{
			return null;
		}
		else
		{
			
			bookingDao.deleteById(bookingId);
			modifyScheduledFlightAdd(scheduledFlight.getScheduleFlightId(),booking2.getNoOfPassangers());
			return booking2;
		}	
	}


	@Override
	public Booking validateBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		Booking booking1=getById(bookingId);
		if(booking1.getBookingId()==(bookingId))
		{
			
			return booking1;
			
		}
		else
		{
			return null;
		}
	}

	@Override
	public Booking getById(BigInteger bookingId) {
		// TODO Auto-generated method stub
		Optional<Booking> optional=bookingDao.findById(bookingId);
		Booking booking1=optional.orElseThrow(()->new RecordNotFoundException("Booking ID does not exist : "+bookingId));
		return booking1;
	}

	@Override
	public List<ScheduledFlight> searchForScheduledFlight(String srcAirport, String dstnAirport, String deptDate) {
		// TODO Auto-generated method stub
		List<ScheduledFlight> list = scheduleFilghtDao.findAll();
		List<ScheduledFlight> list1=new ArrayList<ScheduledFlight>();
		for(int i=0;i<list.size();i++)
		{
			ScheduledFlight sFlight=list.get(i);
			System.out.println(sFlight.getSchedule().getSrcAirport()+" \n"+sFlight.getSchedule().getDstnAirport()+" "+sFlight.getSchedule().getDeptDate());
			if(sFlight.getSchedule().getSrcAirport().equals(srcAirport) && sFlight.getSchedule().getDstnAirport().equals(dstnAirport))
			{
				list1.add(sFlight);
			}
		}
		return list1;
	}
	
	@Override
	public void modifyScheduledFlight(BigInteger scheduledFlightId,int passengersCount) {
		// TODO Auto-generated method stub
		
		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduledFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduledFlightId));
		sFlight.setAvailableSeats(sFlight.getAvailableSeats()-passengersCount);
		ScheduledFlight sFlight2=sFlight;
		scheduleFilghtDao.deleteById(sFlight.getScheduleFlightId());
		scheduleFilghtDao.save(sFlight2);
	
	}
	
	public void modifyScheduledFlightAdd(BigInteger scheduledFlightId,int passengersCount) {
		// TODO Auto-generated method stub
		
		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduledFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduledFlightId));
		sFlight.setAvailableSeats(sFlight.getAvailableSeats()+passengersCount);
		ScheduledFlight sFlight2=sFlight;
		scheduleFilghtDao.deleteById(sFlight.getScheduleFlightId());
		scheduleFilghtDao.save(sFlight2);
		
	}
	

}
