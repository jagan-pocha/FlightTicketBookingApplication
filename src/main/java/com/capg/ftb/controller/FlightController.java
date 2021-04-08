/*
 * Author Mrudhula
 */

package com.capg.ftb.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ftb.dao.FlightDAO;
import com.capg.ftb.model.Flight;
import com.capg.ftb.service.IFlightService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/ftb")
@Api
@CrossOrigin
public class FlightController {
	
	private static final Logger log =LogManager.getLogger(FlightController.class);
	
	@Autowired
	private IFlightService flightService;
	
	@Autowired
	private FlightDAO flightDao;
	
	// Adds a new flight which can be scheduled
	@PostMapping(value="/addFlight")
	public ResponseEntity<Flight> addFlight(@RequestBody Flight flight)
	{
		Flight flight1=flightService.addFlight(flight);
		log.info("Added a flight");
		return new ResponseEntity<Flight>(flight1,HttpStatus.CREATED);
	}
	
	
	
	
	//Modify the details of a flight
		@PutMapping(value="/modifyFlight/{flightNumber}")
		public ResponseEntity<Flight> updateFlight(@PathVariable BigInteger flightNumber,@Valid @RequestBody Flight flight)
		{
			Flight flight1=flightService.modifyFlight(flightNumber);
			
			flight1.setSeatCapacity(flight.getSeatCapacity());
			flight1.setCarrierName(flight.getCarrierName());
			flight1.setFlightModel(flight.getFlightModel());
			
//			flightService.validateFlight(flight1);
//			flightService.deleteFlight(flightNumber);
			Flight flight2=flightDao.save(flight1);
			log.info("Modified a flight");
			return  new ResponseEntity<Flight>(flight2,HttpStatus.OK);
		}
		
		
		
		
	//View the details of a flight specified by the flight number
	@GetMapping(value="/viewFlight/{flightNumber}")
	public ResponseEntity<Flight> viewFlight(@PathVariable BigInteger flightNumber)
	{
		Flight flight=flightService.viewFlight(flightNumber);
		log.info("viewed a flight");
		return new ResponseEntity<Flight>(flight,HttpStatus.OK);
		
	}
	
	
	
	
	// View the details of all flights 
	@GetMapping(value="/viewAllFlights")
	public ResponseEntity<List<Flight>> viewAllFlights()
	{
		List<Flight> flightsList=flightService.viewAllFlights();
		log.info("viewed all flights");
		return new  ResponseEntity<List<Flight>>(flightsList,HttpStatus.OK);
	}
	
	
	
	
	
	// Removes a flight
	@DeleteMapping(value="/deleteFlight/{flightNumber}")
	public ResponseEntity<Flight> deleteFlight(@PathVariable BigInteger flightNumber)
	{
		Flight flight=flightService.deleteFlight(flightNumber);
		log.info("deleted a flight");
		return new ResponseEntity<Flight>(flight,HttpStatus.OK);
	}
		
	
}

