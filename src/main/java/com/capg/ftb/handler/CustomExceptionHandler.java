package com.capg.ftb.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capg.ftb.exception.FlightNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFound(FlightNotFoundException fnfe)
	{
		return new ResponseEntity<String>(fnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
}
