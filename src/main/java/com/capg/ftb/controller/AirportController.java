/* 
 * Author- S.N.V.Manasvi
 */

package com.capg.ftb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ftb.model.Airport;
import com.capg.ftb.service.AirportServiceImpl;
import com.capg.ftb.service.IAirportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping(value="/ftb")
@Validated
@Api(value = "Airport Controller")
public class AirportController {


	private static final Logger log =LogManager.getLogger(AirportController.class);

	@Autowired
	private IAirportService airportService;

	@ApiOperation(value = "Add Airport")
	@PostMapping(value = "/addAirport")
	public ResponseEntity<Airport> addAirport(@Valid @RequestBody Airport airport) {
		log.info("Controller Triggered");
		Airport airport1 = airportService.addAirport(airport);
		return new ResponseEntity<Airport>(airport1, HttpStatus.CREATED);
	}

	@ApiOperation(value = "List of Airports")
	@GetMapping(value = "/viewAllAirports")
	public ResponseEntity<List<Airport>> viewAllAirport() {
		log.info("Controller Triggered");
		List<Airport> airportsList = airportService.viewAllAirport();
		return new ResponseEntity<List<Airport>>(airportsList, HttpStatus.OK);
	}

	@ApiOperation(value = "Airport of a particular Code")
	@GetMapping(value = "/viewAirport/{airportCode}")
	public ResponseEntity<Airport> viewAirport(@PathVariable String airportCode) {
		log.info("Controller Triggered");
		Airport airport = airportService.viewAirport(airportCode);
		return new ResponseEntity<Airport>(airport, HttpStatus.OK);

	}
	
	
}
