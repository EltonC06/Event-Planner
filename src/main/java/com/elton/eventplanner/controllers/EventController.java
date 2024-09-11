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

import com.elton.eventplanner.DTOs.EventDTO;
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
	public EventDTO findById(@PathVariable(name = "id") Long id) {
		EventDTO eventDTO = service.findEventById(id);
		return eventDTO;
	}

	@PostMapping
	public void saveEvent(@RequestBody EventDTO eventDTO) {
		service.saveEvent(eventDTO);
	}
	
	@PutMapping(value = "/{id}")
	public void updateEvent(@PathVariable(name = "id") Long id, @RequestBody EventDTO eventDTO) {
		service.updateEvent(id, eventDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteEvent(@PathVariable(name = "id") Long id) {
		service.deleteEvent(id);
	}
	
	@PutMapping("/cancel/{id}")
	public void cancelEvent(@PathVariable Long id) {
		service.cancelEvent(id);
	}
}
