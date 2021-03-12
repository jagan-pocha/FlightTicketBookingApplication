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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AirportServiceImpl implements IAirportService {
	
	private static final Logger log = (Logger) LogManager.getLogger(AirportServiceImpl.class);
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
		log.info("Service Triggered");
		Optional<Airport> findById = airportDao.findById(airportCode);
		if (findById.isPresent()) {
			return findById.get();
		}

		else
			log.error("Airport with code doesnt exist!");
		throw new RecordNotFoundException("Airport with airport code: " + airportCode + "not exists");
	}

	// Code to Add an Airport
	@Override
	public Airport addAirport(Airport airport) {

		
		Optional<Airport> findById = airportDao.findById(airport.getAirportCode());
		log.info("Service Triggered");
		if (!findById.isPresent()) {
			airportDao.save(airport);
			return airport;

		}

		else
			log.error("Insertion failed! Airport with code already exists!");
		throw new RecordAlreadyPresentException("Airport with code already present");
	}


	
	}