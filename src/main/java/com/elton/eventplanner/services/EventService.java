package com.elton.eventplanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elton.eventplanner.entities.Event;
import com.elton.eventplanner.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository repository;
	
	public List<Event> findAllEvent() {
		return repository.findAll();
	}
	
	public Event findEventById(Long id) {
		return repository.findById(id).get();
	}
	
	public void saveEvent(Event event) {
		repository.save(event);
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
