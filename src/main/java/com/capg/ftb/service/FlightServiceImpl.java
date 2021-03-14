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
import com.capg.ftb.exception.FlightExceptions;
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
		
		if(validateFlight(flight))
		{
		List<Flight> list=flightDao.findAll();
		for(int i=0;i<list.size();i++)
		{
			Flight f=list.get(i);
			if(f.getFlightNumber().compareTo(flight.getFlightNumber())==0)
			{
				throw new FlightExceptions("Flight with given Number already existed");
			}
		}
		Flight flight1=flightDao.save(flight);
		
		
		return flight1;
		}
	
	else
	{
	return null;	
	}
}
	
	
	

	//Modify the details of a flight
	@Override
	public Flight modifyFlight(BigInteger flightNumber) {
		// TODO Auto-generated method stub
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight1=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		
		if(!(flight1.getFlightNumber().compareTo(flightNumber)==0))
		{
			throw new FlightNotFoundException("Flight cannot be modified");
		}
		
		List<ScheduledFlight> list=scheduledFDao.findAll();
		for(int i=0;i<list.size();i++)
		{
			ScheduledFlight sFlight=list.get(0);
			if(sFlight.getFlight().getFlightNumber().compareTo(flightNumber)==0)
			{
				throw new FlightNotFoundException("Flight can not be deleted now , as it is Scheduled to fly");
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
			if(sFlight.getFlight().getFlightNumber().compareTo(flightNumber)==0 && sFlight.getSchedule().getDeptDate().compareTo(df.format(date))>0)
			{
				throw new FlightNotFoundException("Flight can not be deleted now ,as it is Scheduled to fly");
			}
		}
		flightDao.deleteById(flightNumber);
		return flight;
		
	}
	
	
	//validating Flight properties
	@Override
	public boolean validateFlight(Flight flight)
	{
		if(flight.getSeatCapacity()<30)
		{
			throw new FlightExceptions("minimum 30 seats");
		}
		else if(flight.getCarrierName().length()<3 || flight.getFlightModel().length()<3)
		{
			throw new FlightExceptions("Carrier Name and Flight model cannot be less than 3 characters");
		}
		else if(flight.getFlightNumber().compareTo(new BigInteger("555000"))<0 || flight.getFlightNumber().compareTo(new BigInteger("555999"))>0)
		{
			throw new FlightExceptions("Flight Number must be 555000 to 555999");
		}
		else
		{
		return true;
		}
	}

	
}
