/*
 * Author Mrudhula
 */
package com.capg.ftb.service;


import java.util.List;

import com.capg.ftb.model.Flight;

public interface IFlightService {

	public Flight addFlight(Flight flight);
	
	public Flight viewFlight(int flightNumber); // throws FlightNotFoundException;
	
	public List<Flight> viewAllFlights();
	
	public Flight deleteFlight(int flightNumber);
	
	public Flight modifyFlight(int flightNumber);
	
}
