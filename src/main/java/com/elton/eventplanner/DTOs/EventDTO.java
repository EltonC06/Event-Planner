package com.elton.eventplanner.DTOs;

import java.util.Date;

public class EventDTO {
	
	private String name;
	private Date date;
	private String local;
	private String description;
	private String eventStatus;
	private Long userId;
		
	public EventDTO() {
		
	}

	public EventDTO(String name, Date date, String local, String description, Long userId, String eventStatus) {
		super();
		this.name = name;
		this.date = date;
		this.local = local;
		this.description = description;
		this.setEventStatus(eventStatus);
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDescription() {
		return description;
	}
	
	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
