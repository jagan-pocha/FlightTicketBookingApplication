package com.capg.ftb.controller;

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

@RestController
@RequestMapping(value="/ftb")
@Validated
public class ScheduledFlightController {

	@Autowired
	private ScheduledFlightServiceImpl scheduledFService;
	
	@PostMapping(value="/addScheduledFlight")
	public ResponseEntity<ScheduledFlight> addScheduledFlight(@Valid @RequestBody ScheduledFlight scheduledFlight)
	{
		ScheduledFlight scheduledFlight1=scheduledFService.addScheduledFlight(scheduledFlight);
		
		return new ResponseEntity<ScheduledFlight>(scheduledFlight1,HttpStatus.CREATED);
	}
	
	
	@GetMapping(value="/viewAllScheduledFlights")
	public ResponseEntity<List<ScheduledFlight>> viewAllScheduledFlights()
	{
		List<ScheduledFlight> allSFlights=scheduledFService.viewAllScheduledFlights();
		return new ResponseEntity<List<ScheduledFlight>>(allSFlights,HttpStatus.OK);
	}
	
	@GetMapping(value="/viewScheduledFlight/{scheduleFlightId}")
	public ResponseEntity<ScheduledFlight> viewScheduledFlight(@PathVariable int scheduleFlightId)
	{
		ScheduledFlight sFlight=scheduledFService.viewScheduledFlight(scheduleFlightId);
		return new ResponseEntity<ScheduledFlight>(sFlight,HttpStatus.OK);
	}
	

	@PutMapping(value="/modifyScheduledFlight/{scheduleFlightId}")
	public ResponseEntity<ScheduledFlight> mpdifycheduledFlight(@PathVariable int scheduleFlightId)
	{
		ScheduledFlight sFlight=scheduledFService.modifyScheduledFlight(scheduleFlightId);
		scheduledFService.removeScheduledFlight(scheduleFlightId);
		ScheduledFlight sFlight1=scheduledFService.addScheduledFlight(sFlight);
		return new ResponseEntity<ScheduledFlight>(sFlight1,HttpStatus.OK);
	}
	

	@GetMapping(value="/searchScheduledFlight/{srcAirport}/{dstnAirport}/{deptDate}")
	
	public ResponseEntity<List<ScheduledFlight>> searchScheduledFlight(@PathVariable String srcAirport,@PathVariable String dstnAirport,@PathVariable String deptDate)
	{
		List<ScheduledFlight> sFlights=scheduledFService.searchScheduledFlight(srcAirport, dstnAirport,deptDate);
		return new ResponseEntity<List<ScheduledFlight>>(sFlights,HttpStatus.OK);
	}
	
	
}
