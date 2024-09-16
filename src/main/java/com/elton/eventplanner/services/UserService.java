package com.elton.eventplanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elton.eventplanner.DTOs.UserDTO;
import com.elton.eventplanner.entities.Event;
import com.elton.eventplanner.entities.User;
import com.elton.eventplanner.entities.enums.UserRole;
import com.elton.eventplanner.repositories.UserRepository;
import com.elton.eventplanner.services.exceptions.EntityNotFoundException;
import com.elton.eventplanner.services.exceptions.UserAlreadyExistsException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<User> findAllUsers() {
		List<User> userList = repository.findAll();
		return userList;
	}
	
	public UserDTO findUserById(Long id) {
		User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		UserDTO userDTO = convertToDTO(user);
		return userDTO;
	}
	
	public User saveUser(UserDTO userDTO) {
		User user = convertToEntity(userDTO);
		List<User> listOfUser = repository.findAll();
		for (User obj : listOfUser) {
			if (obj.getUserName().equals(user.getUserName())) {
				throw new UserAlreadyExistsException(user.getUserName());
			}
		}
		return repository.save(user);
	}
	
	public User updateUser(Long id, UserDTO userDTO) {
		User user = convertToEntity(userDTO);
		User userToUpdate = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		if (!user.getUserName().equals(userToUpdate.getUserName())) {
			List<User> listOfUsers = repository.findAll();
			for (User obj : listOfUsers) {
				if (obj.getUserName().equals(user.getUserName())) {
					throw new UserAlreadyExistsException(user.getUserName());
				}
			}
		}
		userToUpdate.setUserName(user.getUserName());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setRole(user.getRole());
		
		return repository.save(userToUpdate);
	}
	
	public void deleteUser(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException(id);
		} else {
			repository.deleteById(id);
		}
	}
	
	private User convertToEntity(UserDTO userDTO) {
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		user.setRole(UserRole.valueOf(userDTO.getRole()));
		return user;
	}
	
	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName(user.getUserName());
		userDTO.setRole(user.getRole().toString());
		userDTO.setPassword(user.getPassword());
		for (Event event : user.getEventList()) {
			userDTO.addEventId(event.getId());
		}
		return userDTO;
	}
}
