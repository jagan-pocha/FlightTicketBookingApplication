package com.capg.ftb.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.capg.ftb.exception.AirportNotFoundException;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.exception.RecordAlreadyPresentException;
import com.capg.ftb.exception.RecordNotFoundException;
import com.capg.ftb.exception.SeatsNotAvailableException;
import com.capg.ftb.exception.UserNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

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
	public ResponseEntity<String> handleRecordAlreadyFound(RecordAlreadyPresentException rafe)
	{
		return new ResponseEntity<String>(rafe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<String> handleAirportNotFound(AirportNotFoundException anfe)
	{
		return new ResponseEntity<String>(anfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SeatsNotAvailableException.class)
	public ResponseEntity<String> handleSeatsNotAvailable(SeatsNotAvailableException snae)
	{
		return new ResponseEntity<String>(snae.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotAvailable(UserNotFoundException unfe)
	{
		return new ResponseEntity<String>(unfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	 @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                                                                  HttpHeaders headers,
	                                                                  HttpStatus status, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", new Date());
	        body.put("status", status.value());

	        //Get all errors
	        List<String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(x -> x.getDefaultMessage())
	                .collect(Collectors.toList());

	        body.put("errors", errors);

	        return new ResponseEntity<>(body, headers, status);

	    }

}
