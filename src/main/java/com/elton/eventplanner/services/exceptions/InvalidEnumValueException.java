package com.elton.eventplanner.services.exceptions;

public class InvalidEnumValueException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidEnumValueException(String msg) {
		super("Invalid enum value. " + msg);
	}
}
