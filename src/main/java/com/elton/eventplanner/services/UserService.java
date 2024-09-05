package com.elton.eventplanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elton.eventplanner.entities.User;
import com.elton.eventplanner.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<User> findAllUsers() {
		return repository.findAll();
	}
	
	public User findUserById(Long id) {
		User user = repository.findById(id).get();
		return user;
	}
	
	public void saveUser(User user) {
		List<User> listOfUser = repository.findAll();
		
		for (User obj : listOfUser) {
			if (obj.getUserName().equals(user.getUserName())) {
				throw new RuntimeException();
			}
		}
		
		repository.save(user);
	}
	
	public void updateUser(Long id, User user) {
		User userToUpdate = repository.findById(id).get();
		
		if (!user.getUserName().equals(userToUpdate.getUserName())) {
			List<User> listOfUsers = repository.findAll();
			
			for (User obj : listOfUsers) {
				if (obj.getUserName().equals(user.getUserName())) {
					throw new RuntimeException();
				}
			}
		}
		
		userToUpdate.setUserName(user.getUserName());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setRole(user.getRole());
		
		repository.save(userToUpdate);
	}
	
	public void deleteUser(Long id) {
		repository.deleteById(id);
	}
	
	
	
}
