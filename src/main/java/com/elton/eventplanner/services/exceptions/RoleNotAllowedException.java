package com.elton.eventplanner.services.exceptions;

public class RoleNotAllowedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RoleNotAllowedException(Object role, String action) {
		super("Role '" + role + "' is not allowed to perform the action: " + action);
	}
}
