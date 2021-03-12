/*
 * Author Mrudhula
 */
package com.capg.ftb.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.FlightDAO;
import com.capg.ftb.dao.IScheduleFlightDAO;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.model.Flight;
import com.capg.ftb.model.ScheduledFlight;

@Service
public class FlightServiceImpl implements IFlightService {

	@Autowired
	private FlightDAO flightDao;
	
	@Autowired
	private IScheduleFlightDAO scheduledFDao;
	
	SimpleDateFormat df=new SimpleDateFormat("MM-dd-yyyy");
	Date date=new Date();
	// Adds a new flight which can be scheduled
	@Override
	public Flight addFlight(Flight flight) {
		// TODO Auto-generated method stub
		
		Flight flight1=flightDao.save(flight);
		return flight1;
	}

	//Modify the details of a flight
	@Override
	public Flight modifyFlight(BigInteger flightNumber) {
		// TODO Auto-generated method stub
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight1=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		
		List<ScheduledFlight> list=scheduledFDao.findAll();
		for(int i=0;i<list.size();i++)
		{
			ScheduledFlight sFlight=list.get(0);
			if(sFlight.getFlight().getFlightNumber()==flightNumber && sFlight.getSchedule().getDeptDate().compareTo(df.format(date))>0)
			{
				throw new FlightNotFoundException("Flight can not be deleted now ,as it is Scheduled to fly");
			}
		}
		return flight1;
		
	}

	//View the details of a flight specified by the flight number
	@Override
	public Flight viewFlight(BigInteger flightNumber) {
		// TODO Auto-generated method stub
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		return flight;
	}

	
	// View the details of all flights 
	@Override
	public List<Flight> viewAllFlights() {
		// TODO Auto-generated method stub
		List<Flight> flightsList=(List<Flight>) flightDao.findAll();
		return flightsList;
	}

	// Removes a flight
	@Override
	public Flight deleteFlight(BigInteger flightNumber) 
	{
		
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		
		List<ScheduledFlight> list=scheduledFDao.findAll();
		for(int i=0;i<list.size();i++)
		{
			ScheduledFlight sFlight=list.get(0);
			if(sFlight.getFlight().getFlightNumber()==flightNumber && sFlight.getSchedule().getDeptDate().compareTo(df.format(date))>0)
			{
				throw new FlightNotFoundException("Flight can not be deleted now ,as it is Scheduled to fly");
			}
		}
		flightDao.deleteById(flightNumber);
		return flight;
		
	}

	
}
