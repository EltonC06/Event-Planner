package com.elton.eventplanner.services.exceptions;

public class WrongDateFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public WrongDateFormatException() {
		super("Invalid date format. Please use 'yyyy-MM-dd' format (e.g., 2024-09-12)");
	}
}
