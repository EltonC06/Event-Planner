package com.elton.eventplanner.entities;

import java.util.List;
import java.util.Objects;

import com.elton.eventplanner.entities.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private String password;
	private UserRole role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Event> eventList;
	
	public User() {
		
	}

	public User(Long id, String userName, String password, UserRole role) {
		super();
		this.userId = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
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
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<Event> getEventList() {
		return eventList;
	}
	
	public void addEvent(Event event) {
		this.eventList.add(event);
	}
 
	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(userId, other.userId);
	}
}
