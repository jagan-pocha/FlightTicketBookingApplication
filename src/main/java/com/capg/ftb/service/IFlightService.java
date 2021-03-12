/*
 * Author Mrudhula
 */
package com.capg.ftb.service;


import java.math.BigInteger;
import java.util.List;

import com.capg.ftb.model.Flight;

public interface IFlightService {

	public Flight addFlight(Flight flight);
	
	public Flight viewFlight(BigInteger flightNumber); // throws FlightNotFoundException;
	
	public List<Flight> viewAllFlights();
	
	public Flight deleteFlight(BigInteger flightNumber);
	
	public Flight modifyFlight(BigInteger flightNumber);
	
}
