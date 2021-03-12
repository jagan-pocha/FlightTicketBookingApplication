/*
 * Jagan Mohan
 */
package com.capg.ftb.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ftb.model.ScheduledFlight;
import com.capg.ftb.service.ScheduledFlightServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
@RequestMapping(value="/ftb")
@Validated
@Api
public class ScheduledFlightController {

	
	private static final Logger log =LogManager.getLogger(AirportController.class);
		
	
	@Autowired
	private ScheduledFlightServiceImpl scheduledFService;
	
	
	//Method to schedule the Flight
	@ApiOperation(value = "Scheduling a Flight")
	@PostMapping(value="/addScheduledFlight")
	public ResponseEntity<ScheduledFlight> addScheduledFlight(@Valid @RequestBody ScheduledFlight scheduledFlight)
	{
		ScheduledFlight scheduledFlight1=scheduledFService.addScheduledFlight(scheduledFlight);
		log.info("Scheduled a Flight");
		
		return new ResponseEntity<ScheduledFlight>(scheduledFlight1,HttpStatus.CREATED);
	}
	
	// mathod to view all scheduled Flights
	@ApiOperation(value = "To View all Scheduled Flights")
	@GetMapping(value="/viewAllScheduledFlights")
	public ResponseEntity<List<ScheduledFlight>> viewAllScheduledFlights()
	{
		List<ScheduledFlight> allSFlights=scheduledFService.viewAllScheduledFlights();
		log.info("Viewed all scheduled Flight");
		return new ResponseEntity<List<ScheduledFlight>>(allSFlights,HttpStatus.OK);
	}
	
	//method to view the  scheduled Flight using scheduled Id
	@ApiOperation(value = "To view a Scheduled Flight")
	@GetMapping(value="/viewScheduledFlight/{scheduleFlightId}")
	public ResponseEntity<ScheduledFlight> viewScheduledFlight(@PathVariable int scheduleFlightId)
	{
		ScheduledFlight sFlight=scheduledFService.viewScheduledFlight(scheduleFlightId);
		log.info("viewed a Scheduled  Flight");
		return new ResponseEntity<ScheduledFlight>(sFlight,HttpStatus.OK);
	}
	

	//method to modify the scheduled flight
	@ApiOperation(value = "modify a Scheduled a Flight")
	@PutMapping(value="/modifyScheduledFlight/{scheduleFlightId}")
	public ResponseEntity<ScheduledFlight> modifycheduledFlight(@Valid @RequestBody ScheduledFlight scheduledFlight,@PathVariable int scheduleFlightId)
	{
		ScheduledFlight sFlight=scheduledFService.modifyScheduledFlight(scheduledFlight,scheduleFlightId);
		ScheduledFlight sFlight1=scheduledFService.addScheduledFlight(sFlight);
		log.info("modified a Scheduled  Flight");
		return new ResponseEntity<ScheduledFlight>(sFlight1,HttpStatus.OK);
	}
	
	//search the flight based on source ,detination and travelling date
	
	@ApiOperation(value = "Search for a Scheduled Flight")
	@GetMapping(value="/searchScheduledFlight/{srcAirport}/{dstnAirport}/{deptDate}")
	
	public ResponseEntity<List<ScheduledFlight>> searchScheduledFlight(@PathVariable String srcAirport,@PathVariable String dstnAirport,@PathVariable String deptDate)
	{
		List<ScheduledFlight> sFlights=scheduledFService.searchScheduledFlight(srcAirport, dstnAirport,deptDate);
		log.info("Searched for a  Scheduled Flight");
		return new ResponseEntity<List<ScheduledFlight>>(sFlights,HttpStatus.OK);
	}
	
	
}
