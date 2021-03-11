package com.capg.ftb.exception;

public class UserNotFoundException extends RuntimeException{
	

	public UserNotFoundException()
	{
		
	}
	
	public UserNotFoundException(String s)
	{
		super(s);
	}
}
