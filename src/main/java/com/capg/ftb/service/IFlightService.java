package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.capg.ftb.model.Flight;

public interface IFlightService {

	public Flight addFlight(Flight flight);
	
	public Flight viewFlight(int flightNumber); // throws FlightNotFoundException;
	
	public List<Flight> viewAllFlights();
	
	public Flight removeFlight(int flightNumber);
	
	public Flight updateFlight(int flightNumber);
	
}
