package com.elton.eventplanner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.elton.eventplanner.DTOs.UserDTO;
import com.elton.eventplanner.services.EventService;
import com.elton.eventplanner.services.UserService;

@Configuration
@Profile("Test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	UserService userService;
	
	@Autowired
	EventService eventService;
	
	@Override
	public void run(String... args) throws Exception {
		
		UserDTO user = new UserDTO();
		user.setUserName("Elton");
		user.setRole("OWNER");
		user.setPassword("12312");
		UserDTO user2 = new UserDTO();
		user2.setUserName("Jon");
		user2.setRole("ADM");
		user2.setPassword("12312");
		UserDTO user3 = new UserDTO();
		user3.setUserName("Alex");
		user3.setRole("USER");
		user3.setPassword("12312");
		
		userService.saveUser(user);
		userService.saveUser(user2);
		userService.saveUser(user3);
		
		
	}
}
