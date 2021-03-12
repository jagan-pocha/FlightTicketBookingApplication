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
import com.capg.ftb.service.IAirportService;


@RestController
@RequestMapping(value="/ftb")
@Validated
public class AirportController {

	@Autowired
	private IAirportService airportService;

	@PostMapping(value = "/addAirport")
	public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {

		Airport airport1 = airportService.addAirport(airport);
		return new ResponseEntity<Airport>(airport1, HttpStatus.CREATED);
	}

	@GetMapping(value = "/viewAllAirports")
	public ResponseEntity<List<Airport>> viewAllAirport() {
		List<Airport> airportsList = airportService.viewAllAirport();
		return new ResponseEntity<List<Airport>>(airportsList, HttpStatus.OK);
	}

	@GetMapping(value = "/viewAirport/{airportCode}")
	public ResponseEntity<Airport> viewAirport(@PathVariable String airportCode) {
		Airport airport = airportService.viewAirport(airportCode);
		return new ResponseEntity<Airport>(airport, HttpStatus.OK);

	}
	
	
}
