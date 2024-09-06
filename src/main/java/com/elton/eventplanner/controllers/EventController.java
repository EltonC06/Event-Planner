package com.elton.eventplanner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elton.eventplanner.entities.Event;
import com.elton.eventplanner.services.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	EventService service;
	
	@GetMapping
	public List<Event> findAll() {
		return service.findAllEvent();
	}
	
	@GetMapping(value = "/{id}")
	public Event findById(@PathVariable(name = "id") Long id) {
		return service.findEventById(id);
	}
	
	@PostMapping
	public void saveEvent(@RequestBody Event event) {
		service.saveEvent(event);
	}
	
	@PutMapping(value = "/{id}")
	public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
		service.updateEvent(id, event);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteEvent(@PathVariable(name = "id") Long id) {
		service.deleteEvent(id);
	}
}
