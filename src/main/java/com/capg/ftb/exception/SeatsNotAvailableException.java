package com.capg.ftb.exception;

public class SeatsNotAvailableException extends RuntimeException {
	
	public SeatsNotAvailableException() {}
	
	public SeatsNotAvailableException(String s) {
	
		super(s);
	}

}
