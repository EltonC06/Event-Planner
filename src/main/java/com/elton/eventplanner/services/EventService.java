package com.elton.eventplanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elton.eventplanner.DTOs.EventDTO;
import com.elton.eventplanner.entities.Event;
import com.elton.eventplanner.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository repository;
	
	@Autowired
	UserService userService;
	
	public List<Event> findAllEvent() {
		return repository.findAll();
	}
	
	public Event findEventById(Long id) {
		return repository.findById(id).get();
	}
	
	public void saveEvent(EventDTO eventDTO) {		
		Event event = convertToEntity(eventDTO);

		repository.save(event);
	}
	
	private Event convertToEntity(EventDTO eventDTO) {
		Event eventConverted = new Event();
		eventConverted.setDate(eventDTO.getDate());
		eventConverted.setDescription(eventDTO.getDescription());
		eventConverted.setLocal(eventDTO.getLocal());
		eventConverted.setName(eventDTO.getName());
		eventConverted.setUser(userService.findUserById(eventDTO.getUserId()));
		
		return eventConverted;
	}

	public void updateEvent(Long id, Event event) {
		Event eventToUpdate = repository.findById(id).get();
		
		eventToUpdate.setDate(event.getDate());
		eventToUpdate.setDescription(event.getDescription());
		eventToUpdate.setLocal(event.getLocal());
		eventToUpdate.setName(event.getName());
		
		repository.save(eventToUpdate);
	}
	
	public void deleteEvent(Long id) {
		repository.deleteById(id);
	}
}
