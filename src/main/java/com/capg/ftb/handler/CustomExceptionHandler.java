package com.capg.ftb.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.exception.RecordAlreadyPresentException;
import com.capg.ftb.exception.RecordNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<String> handleFlightNotFound(FlightNotFoundException fnfe)
	{
		return new ResponseEntity<String>(fnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<String> handleRecordNotFound(RecordNotFoundException rnfe)
	{
		return new ResponseEntity<String>(rnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public ResponseEntity<String> handleRecordNotFound(RecordAlreadyPresentException fnfe)
	{
		return new ResponseEntity<String>(fnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
}
