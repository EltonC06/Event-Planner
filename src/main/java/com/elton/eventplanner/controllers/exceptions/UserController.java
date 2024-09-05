package com.elton.eventplanner.controllers.exceptions;

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

import com.elton.eventplanner.entities.User;
import com.elton.eventplanner.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserRepository repository;
	
	@GetMapping
	public List<User> findAll() {
		return repository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public User findById(@PathVariable(name = "id") Long id) {
		return repository.findById(id).get();
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		repository.save(user);
	}
	
	@PutMapping(value = "/{id}")
	public void updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
		User userToUpdate = repository.findById(id).get();
		
		userToUpdate.setUserName(user.getUserName());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setRole(user.getRole());
		
		repository.save(userToUpdate);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
}
