package com.elton.eventplanner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

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
	public ResponseEntity<EventDTO> findById(@PathVariable(name = "id") Long id) {
		EventDTO eventDTO = service.findEventById(id);
		return ResponseEntity.ok().body(eventDTO);
	}

	@PostMapping
	public ResponseEntity<Event> saveEvent(@Valid @RequestBody EventDTO eventDTO) {
		Event event = service.saveEvent(eventDTO);
		return ResponseEntity.ok().body(event);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable(name = "id") Long id, @Valid @RequestBody EventDTO eventDTO) {
		Event event = service.updateEvent(id, eventDTO);
		return ResponseEntity.ok().body(event);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteEvent(@PathVariable(name = "id") Long id) {
		service.deleteEvent(id);
	}
	
	@PutMapping("/cancel/{id}")
	public ResponseEntity<Event> cancelEvent(@PathVariable Long id) {
		Event event = service.cancelEvent(id);
		return ResponseEntity.ok().body(event);
	}
	
	@PutMapping("/autostatusupdate/{id}")
	public ResponseEntity<Event> autoStatusUpdate(@PathVariable Long id) {
		Event event = service.autoStatusUpdate(id);
		return ResponseEntity.ok().body(event);
	}
}
