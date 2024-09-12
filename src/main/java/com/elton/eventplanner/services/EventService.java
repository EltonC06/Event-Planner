package com.elton.eventplanner.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elton.eventplanner.DTOs.EventDTO;
import com.elton.eventplanner.entities.Event;
import com.elton.eventplanner.entities.enums.EventStatus;
import com.elton.eventplanner.entities.enums.UserRole;
import com.elton.eventplanner.repositories.EventRepository;
import com.elton.eventplanner.repositories.UserRepository;
import com.elton.eventplanner.services.exceptions.EntityNotFoundException;
import com.elton.eventplanner.services.exceptions.RoleNotAllowedException;

@Service
public class EventService {
	
	@Autowired
	EventRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<Event> findAllEvent() {
		return repository.findAll();
	}
	
	public EventDTO findEventById(Long id) {
		Event event = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		EventDTO eventDTO = convertToDTO(event);
		return eventDTO;
	}
	
	public void saveEvent(EventDTO eventDTO) {		
		Event event = convertToEntity(eventDTO);
		if (!userRepository.existsById(event.getUser().getId())) {
			throw new EntityNotFoundException(event.getUser().getId());
		}
		if (event.getUser().getRole().equals(UserRole.USER)) {
			throw new RoleNotAllowedException(event.getUser().getRole(), "create an event");
		} else {
			repository.save(event);
		}
	}
	
	public void updateEvent(Long id, EventDTO eventDTO) {
		Event eventToUpdate = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		Event event = convertToEntity(eventDTO);
		if (userRepository.existsById(event.getUser().getId())) {
			throw new EntityNotFoundException(event.getUser().getId());
		}
		if (event.getUser().getRole().equals(UserRole.USER)) {
			throw new RuntimeException();
		} else {
			eventToUpdate.setDate(event.getDate());
			eventToUpdate.setDescription(event.getDescription());
			eventToUpdate.setLocal(event.getLocal());
			eventToUpdate.setName(event.getName());
			eventToUpdate.setUser(event.getUser());
			eventToUpdate.setEventStatus(event.getEventStatus());
			repository.save(eventToUpdate);
		}
	}
	
	public void deleteEvent(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException(id);
		} else {
			repository.deleteById(id);
		}
	}
	
	public void cancelEvent(Long id) {
		Event event = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		event.setEventStatus(EventStatus.CANCELLED);
		repository.save(event);
	}
	
	public void autoStatusUpdate(Long id) {
		Event event = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		Date calendar = Calendar.getInstance().getTime();
		
		if (event.getDate().before(calendar) && !event.getEventStatus().equals(EventStatus.CANCELLED)) {
			event.setEventStatus(EventStatus.COMPLETED);
			repository.save(event);
		} else {
			if (event.getDate().after(calendar) && !event.getEventStatus().equals(EventStatus.CANCELLED)) {
				event.setEventStatus(EventStatus.PLANNED);
				repository.save(event);
			}
		}
	}
	
	private Event convertToEntity(EventDTO eventDTO) {
		Event eventConverted = new Event();
		if (!userRepository.existsById(eventDTO.getUserId())) {
			throw new EntityNotFoundException(eventDTO.getUserId());
		}
		eventConverted.setDate(eventDTO.getDate());
		eventConverted.setDescription(eventDTO.getDescription());
		eventConverted.setLocal(eventDTO.getLocal());
		eventConverted.setName(eventDTO.getName());
		eventConverted.setUser(userRepository.findById(eventDTO.getUserId()).get());
		eventConverted.setEventStatus(EventStatus.valueOf(eventDTO.getEventStatus()));
		return eventConverted;
	}
	
	private EventDTO convertToDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setName(event.getName());
		eventDTO.setDescription(event.getDescription());
		eventDTO.setLocal(event.getLocal());
		eventDTO.setDate(event.getDate());
		eventDTO.setUserId(event.getUser().getId());
		eventDTO.setEventStatus(event.getEventStatus().toString());
		return eventDTO;
	}
}
