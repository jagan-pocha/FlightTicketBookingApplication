/*
 * Author Mrudhula
 */

package com.capg.ftb.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ftb.model.Flight;
import com.capg.ftb.service.IFlightService;

@RestController
@RequestMapping(value="/ftb")
@Validated
public class FlightController {
	
	@Autowired
	private IFlightService flightService;
	
	// Adds a new flight which can be scheduled
	@PostMapping(value="/addFlight")
	public ResponseEntity<Flight> addFlight(@RequestBody Flight flight)
	{
		Flight flight1=flightService.addFlight(flight);
		return new ResponseEntity<Flight>(flight1,HttpStatus.CREATED);
	}
	
	//Modify the details of a flight
		@PutMapping(value="/modifyFlight/{flightNumber}")
		public ResponseEntity<Flight> updateFlight(@PathVariable int flightNumber,@RequestBody Flight flight)
		{
			Flight flight1=flightService.modifyFlight(flightNumber);
			flight1.setSeatCapacity(flight.getSeatCapacity());
			flight1.setCarrierName(flight.getCarrierName());
			flight1.setFlightModel(flight.getFlightModel());
			Flight flight2=flightService.addFlight(flight1);
			return  new ResponseEntity<Flight>(flight2,HttpStatus.OK);
		}
		
		
	//View the details of a flight specified by the flight number
	@GetMapping(value="/viewFlight/{flightNumber}")
	public ResponseEntity<Flight> viewFlight(@PathVariable int flightNumber)
	{
		Flight flight=flightService.viewFlight(flightNumber);
		return new ResponseEntity<Flight>(flight,HttpStatus.OK);
		
	}
	
	// View the details of all flights 
	@GetMapping(value="/viewAllFlights")
	public ResponseEntity<List<Flight>> viewAllFlights()
	{
		List<Flight> flightsList=flightService.viewAllFlights();
		return new  ResponseEntity<List<Flight>>(flightsList,HttpStatus.OK);
	}
	
	
	// Removes a flight
	@DeleteMapping(value="/deleteFlight/{flightNumber}")
	public ResponseEntity<Flight> deleteFlight(@PathVariable int flightNumber)
	{
		Flight flight=flightService.deleteFlight(flightNumber);
		return new ResponseEntity<Flight>(flight,HttpStatus.OK);
	}
		
	
}

