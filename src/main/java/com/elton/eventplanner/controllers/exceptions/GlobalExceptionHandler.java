package com.elton.eventplanner.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.elton.eventplanner.services.exceptions.EntityNotFoundException;
import com.elton.eventplanner.services.exceptions.InvalidEnumValueException;
import com.elton.eventplanner.services.exceptions.RoleNotAllowedException;
import com.elton.eventplanner.services.exceptions.UserAlreadyExistsException;
import com.elton.eventplanner.services.exceptions.WrongDateFormatException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<StandardError> userAlreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setError("Database conflict");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(err);
	}
	
	@ExceptionHandler(RoleNotAllowedException.class)
	public ResponseEntity<StandardError> roleNotAllowed(RoleNotAllowedException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.FORBIDDEN.value());
		err.setError("Access denied");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(err);
	}
	
	@ExceptionHandler(WrongDateFormatException.class)
	public ResponseEntity<StandardError> wrongDateFormat(WrongDateFormatException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Invalid date format");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
	}
	
	@ExceptionHandler(InvalidEnumValueException.class)
	public ResponseEntity<StandardError> invalidEnumValue(InvalidEnumValueException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Invalid Enum Value");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationHandling(MethodArgumentNotValidException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Validation error");
		StringBuilder message = new StringBuilder();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			message.append(fieldName).append(": ").append(errorMessage).append("; ");
		});
		err.setMessage(message.toString().trim());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
	}
}
