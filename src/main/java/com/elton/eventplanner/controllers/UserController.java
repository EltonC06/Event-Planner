package com.elton.eventplanner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public User findById(@PathVariable(name = "id") Long id) {
		return service.findUserById(id);
	}
	
	@PostMapping
	public void createUser(@RequestBody UserDTO userDTO) {
		service.saveUser(userDTO);
	}
	
	@PutMapping(value = "/{id}")
	public void updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDTO userDTO) {
		service.updateUser(id, userDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		service.deleteUser(id);
	}
}
