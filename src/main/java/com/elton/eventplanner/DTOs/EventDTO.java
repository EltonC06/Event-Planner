package com.elton.eventplanner.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EventDTO {
	
	@NotBlank(message = "Username can't be empty")
	@Size(min = 4, max = 200, message = "Event name need to have more than 4 and less than 200 characters")
	private String name;
	private String date;
	@Size(min = 4, max = 200, message = "Event local need to have more than 4 and less than 200 characters")
	private String local;
	@Size(min = 10, max = 500, message = "Event description need to have more than 4 and less than 200 characters")
	private String description;
	private String eventStatus;
	private Long userId;
		
	public EventDTO() {
		
	}

	public EventDTO(String name, String date, String local, String description, Long userId, String eventStatus) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
