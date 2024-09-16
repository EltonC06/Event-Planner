package com.elton.eventplanner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elton.eventplanner.DTOs.UserDTO;
import com.elton.eventplanner.entities.User;
import com.elton.eventplanner.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping
	public List<User> findAll() {
		return service.findAllUsers();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") Long id) {
		UserDTO userDTO = service.findUserById(id);
		return ResponseEntity.ok().body(userDTO);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
		User user = service.saveUser(userDTO);
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(name = "id") Long id, @Valid @RequestBody UserDTO userDTO) {
		User user = service.updateUser(id, userDTO);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		service.deleteUser(id);
	}
}
