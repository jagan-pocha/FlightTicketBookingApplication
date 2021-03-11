/*
 * Author Mrudhula
 */
package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.FlightDAO;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.model.Flight;

@Service
public class FlightServiceImpl implements IFlightService {

	@Autowired
	private FlightDAO flightDao;
	
	// Adds a new flight which can be scheduled
	@Override
	public Flight addFlight(Flight flight) {
		// TODO Auto-generated method stub
		
		Flight flight1=flightDao.save(flight);
		return flight1;
	}

	//Modify the details of a flight
	@Override
	public Flight modifyFlight(int flightNumber) {
		// TODO Auto-generated method stub
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight1=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		return flight1;
		
	}

	//View the details of a flight specified by the flight number
	@Override
	public Flight viewFlight(int flightNumber) {
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
	public Flight deleteFlight(int flightNumber) 
	{
		
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		flightDao.deleteById(flightNumber);
		return flight;
		
	}

	
}
