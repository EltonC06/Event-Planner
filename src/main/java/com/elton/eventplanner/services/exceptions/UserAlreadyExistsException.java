package com.elton.eventplanner.services.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(Object username) {
		super(username + " username is already in use");
	}
}
