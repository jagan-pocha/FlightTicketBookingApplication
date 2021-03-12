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

import com.capg.ftb.model.Booking;
import com.capg.ftb.service.BoookingServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/ftb")
@Api
public class BookingController {
	
	@Autowired
	private BoookingServiceImpl bookingService;
	
	@PostMapping(value="/addBooking")
	public ResponseEntity<Booking> addBooking(@Valid @RequestBody Booking booking)
	{
		Booking booking1=bookingService.addBooking(booking);
		return new ResponseEntity<Booking>(booking1,HttpStatus.CREATED);
	}

}
