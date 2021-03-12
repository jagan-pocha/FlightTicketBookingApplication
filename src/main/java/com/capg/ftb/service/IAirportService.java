//Author-S.N.V.Manasvi
package com.capg.ftb.service;


import java.util.List;

import com.capg.ftb.model.Airport;

public interface IAirportService {
	
	public List<Airport> viewAllAirport();

	public Airport viewAirport(String airportCode);

	public Airport addAirport(Airport airport);
}
