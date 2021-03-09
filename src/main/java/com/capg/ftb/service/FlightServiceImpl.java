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
public class FlightServiceImpl implements IFlightService{

	@Autowired
	private FlightDAO flightDao;
	
	@Override
	public Flight addFlight(Flight flight) {
		// TODO Auto-generated method stub
		
		Flight flight1=flightDao.save(flight);
		return flight1;
	}

	@Override
	public Flight viewFlight(BigInteger flightNumber) {
		// TODO Auto-generated method stub
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		return flight;
	}

	@Override
	public List<Flight> viewAllFlights() {
		// TODO Auto-generated method stub
		List<Flight> flightsList=(List<Flight>) flightDao.findAll();
		return flightsList;
	}

	@Override
	public Flight removeFlight(BigInteger flightNumber) 
	{
		
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		flightDao.deleteById(flightNumber);
		return flight;
		
	}

	@Override
	public Flight updateFlight(BigInteger flightNumber) {
		// TODO Auto-generated method stub
		Optional<Flight> optional=flightDao.findById(flightNumber);
		Flight flight1=optional.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the id : "+flightNumber));
		return flight1;
		
	}

}
