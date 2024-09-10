package com.elton.eventplanner.DTOs;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

	private String userName;
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
