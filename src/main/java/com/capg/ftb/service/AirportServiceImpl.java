package com.capg.ftb.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.IAirportDAO;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.exception.RecordAlreadyPresentException;
import com.capg.ftb.exception.RecordNotFoundException;
import com.capg.ftb.model.Airport;


@Service
public class AirportServiceImpl implements IAirportService {
	
	@Autowired
	private IAirportDAO airportDao;

	// Code to View all Airports
	@Override
	public List<Airport> viewAllAirport() {

		List<Airport> airportList = (List<Airport>) airportDao.findAll();
		return airportList;
	}

	// Code to View Airport by Airport Code
	@Override
	public Airport viewAirport(String airportCode) {
		Optional<Airport> findById = airportDao.findById(airportCode);
		if (findById.isPresent()) {
			return findById.get();
		}

		else
			throw new RecordNotFoundException("Airport with airport code: " + airportCode + "not exists");
	}

	// Code to Add an Airport
	@Override
	public Airport addAirport(Airport airport) {

		Optional<Airport> findById = airportDao.findById(airport.getAirportCode());

		if (!findById.isPresent()) {
			airportDao.save(airport);
			return airport;

		}

		else
			throw new RecordAlreadyPresentException("Airport with code already present");
	}


	
	}