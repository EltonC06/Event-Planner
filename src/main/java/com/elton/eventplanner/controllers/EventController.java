package com.elton.eventplanner.controllers;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elton.eventplanner.DTOs.EventDTO;
import com.elton.eventplanner.entities.Event;
import com.elton.eventplanner.services.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	EventService eventService;
	
	@GetMapping
	public ResponseEntity<List<Event>> findAll() {
		List<Event> events = eventService.findAllEvent();
		return ResponseEntity.ok().body(events);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EventDTO> findById(@PathVariable(name = "id") Long id) {
		EventDTO eventDTO = eventService.findEventById(id);
		return ResponseEntity.ok().body(eventDTO);
	}

	@PostMapping
	public ResponseEntity<Event> saveEvent(@Valid @RequestBody EventDTO eventDTO) {
		Event event = eventService.saveEvent(eventDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(event.getId())
				.toUri();
		return ResponseEntity.created(uri).body(event);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable(name = "id") Long id, @Valid @RequestBody EventDTO eventDTO) {
		Event event = eventService.updateEvent(id, eventDTO);
		return ResponseEntity.ok().body(event);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable(name = "id") Long id) {
		eventService.deleteEvent(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/cancel/{id}")
	public ResponseEntity<Event> cancelEvent(@PathVariable Long id) {
		Event event = eventService.cancelEvent(id);
		return ResponseEntity.ok().body(event);
	}
	
	@PutMapping("/autostatusupdate/{id}")
	public ResponseEntity<Event> autoStatusUpdate(@PathVariable Long id) {
		Event event = eventService.autoStatusUpdate(id);
		return ResponseEntity.ok().body(event);
	}
}
