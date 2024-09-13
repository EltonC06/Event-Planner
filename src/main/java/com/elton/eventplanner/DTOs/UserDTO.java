package com.elton.eventplanner.DTOs;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@NotBlank(message = "Username can't be empty")
	@Size(min = 3, max = 18, message = "Username need to have more than 2 and less than 18 characters")
	@Pattern(regexp = "^([a-zA-Z])[a-zA-Z_-]*[\\w_-]*[\\S]$|^([a-zA-Z])[0-9_-]*[\\S]$|^[a-zA-Z]*[\\S]$",
	message = "Username must start with a letter and not contain spaces at the end")
	private String userName;
	@Pattern(regexp = "^(?=.*\\d).{4,8}$", message = "Password must be between 4 and 8 digits long and include at least one numeric digit")
	private String password;
	private String role;
	private List<Long> eventIdList = new ArrayList<Long>();
	
	public UserDTO() {
		
	}
	
	public UserDTO(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Long> getEventIdList() {
		return eventIdList;
	}

	public void addEventId(Long id) {
		eventIdList.add(id);
	}
}
