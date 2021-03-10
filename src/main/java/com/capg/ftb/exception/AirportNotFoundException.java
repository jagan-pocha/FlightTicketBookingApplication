package com.capg.ftb.exception;

public class AirportNotFoundException extends RuntimeException{

	public AirportNotFoundException()
	{
		
	}
	public AirportNotFoundException(String s)
	{
		super(s);
	}
}
