package com.capg.ftb.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ftb.model.Flight;
import com.capg.ftb.model.Users;
import com.capg.ftb.service.IFlightService;
import com.capg.ftb.service.IUsersService;

@RestController
@RequestMapping(value="/ftb")
public class MainController {

	@Autowired
	private IUsersService usersService;
	
	@Autowired
	private IFlightService flightService;
	
	
	
	//User Services
	
	@PostMapping(value="/addUser")
	public ResponseEntity<String> addUser(@RequestBody Users user)
	{
		Users user1=usersService.addUser(user);
		if(user1!=null)
		{
		    return new ResponseEntity<String>("Welcome "+user1.getUserName()+" ! your "+user1.getUserType() +" Acount has been Created Successfully",HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<String>(" Acount has already existed with the given username",HttpStatus.OK);
		}
	}
	
	
	
	@GetMapping(value="/login")
	public ResponseEntity<String> validateUser(@RequestBody Users user)
	{
	 
       Users user1=usersService.validateUser(user);
		if(user1!=null)
		{
		return new ResponseEntity<String>("Welcome "+user1.getUserType()+" : "+user1.getUserName()+" Thank you for loggingIn .",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>(" Invalid Credentials ",HttpStatus.OK);		
		}
	}
	
	
	
	
	// Flight Services
	
	@PostMapping(value="/addFlight")
	public ResponseEntity<Flight> addFlight(@RequestBody @Valid Flight flight)
	{
		Flight flight1=flightService.addFlight(flight);
		return new ResponseEntity<Flight>(flight1,HttpStatus.CREATED);
	}
	
	
	@GetMapping(value="/viewFlight/{flightNumber}")
	public ResponseEntity<Flight> viewFlight(@PathVariable int flightNumber)
	{
		Flight flight=flightService.viewFlight(flightNumber);
		return new ResponseEntity<Flight>(flight,HttpStatus.OK);
		
	}
	
	
	@GetMapping(value="/viewAllFlights")
	public ResponseEntity<List<Flight>> viewAllFlights()
	{
		List<Flight> flightsList=flightService.viewAllFlights();
		return new  ResponseEntity<List<Flight>>(flightsList,HttpStatus.OK);
	}
	
	
	@DeleteMapping(value="/deleteFlight/{flightNumber}")
	public ResponseEntity<Flight> deleteFlight(@PathVariable int flightNumber)
	{
		Flight flight=flightService.removeFlight(flightNumber);
		return new ResponseEntity<Flight>(flight,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateFlight/{flightNumber}")
	public ResponseEntity<Flight> updateFlight(@PathVariable int flightNumber,@RequestBody Flight flight)
	{
		Flight flight1=flightService.updateFlight(flightNumber);
		flight1.setSeatCapacity(flight.getSeatCapacity());
		flight1.setCarrierName(flight.getCarrierName());
		flight1.setFlightModel(flight.getFlightModel());
		Flight flight2=flightService.addFlight(flight1);
		return  new ResponseEntity<Flight>(flight2,HttpStatus.OK);
	}
	
	
}
