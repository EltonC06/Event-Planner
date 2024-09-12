package com.elton.eventplanner.services.exceptions;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Object id) {
		super("Id not found " + id);
	}
}
