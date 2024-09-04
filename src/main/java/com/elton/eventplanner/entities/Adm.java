package com.elton.eventplanner.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_adm")
public class Adm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long admId;
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "adm")
	private List<Event> eventList;
	
	public Adm() {
		
	}

	public Adm(Long id, String password) {
		super();
		this.admId = id;
		this.password = password;
	}

	public Long getId() {
		return admId;
	}

	public void setId(Long id) {
		this.admId = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Event> getEventList() {
		return eventList;
	}
	
	public void addEvent(Event event) {
		this.eventList.add(event);
	}
 
	@Override
	public int hashCode() {
		return Objects.hash(admId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adm other = (Adm) obj;
		return Objects.equals(admId, other.admId);
	}
}
