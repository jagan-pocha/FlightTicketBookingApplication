package com.capg.ftb.exception;


public class RecordAlreadyPresentException extends RuntimeException {
	
	public RecordAlreadyPresentException() {
		
	}
	
	public RecordAlreadyPresentException(String s) {
		super(s);
	}
}
