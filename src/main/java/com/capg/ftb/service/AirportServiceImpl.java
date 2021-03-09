package com.capg.ftb.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.AirportDAO;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.exception.RecordAlreadyPresentException;
import com.capg.ftb.exception.RecordNotFoundException;
import com.capg.ftb.model.Airport;


@Service
public class AirportServiceImpl implements IAirportService {
	
	@Autowired
	private AirportDAO airportDao;

	// View all Airports
	@Override
	public List<Airport> viewAllAirport() {
		
		List<Airport> airportList=(List<Airport>) airportDao.findAll();
		return airportList;
	}

	// View Airport by Airport Code
	@Override
	public Airport viewAirport(String airportCode) {
		Optional<Airport> findById = airportDao.findById(airportCode);
		if (findById.isPresent()) {
			return findById.get();
		}

		else
			throw new RecordNotFoundException("Airport with airport code: " + airportCode + "not exists");
	}

	// Add an Airport

	
	 
	@Override 
	public Airport addAirport(Airport airport) {
//	  Optional<Airport> optional = airportDao.findById(airport.getAirportCode());
//	  Airport airport1=(Airport)optional.orElseThrow(()->new RecordAlreadyPresentException("Airport already existed with code :"+airport.getAirportCode()));
     Airport airport2=airportDao.save(airport);
	 return airport2;
	  
	 }
	
	// Modify an Airport
	@Override
	public Airport modifyAirport(Airport airport) {
		Optional<Airport> findById = airportDao.findById(airport.getAirportCode());
		if (findById.isPresent()) {
			airportDao.save(airport);
		} else
			throw new RecordNotFoundException("Airport with code: " + airport.getAirportCode() + "does not exist");
		return airport;
	}
	

	// Remove an Airport
	@Override
	public String removeAirport(String airportCode) {
		Optional<Airport> findById = airportDao.findById(airportCode);
		if (findById.isPresent()) {
			airportDao.deleteById(airportCode);
			return "Airport removed";
		} else
			throw new RecordNotFoundException("Airport with code: " + airportCode + " not exists");

	}

	
	}