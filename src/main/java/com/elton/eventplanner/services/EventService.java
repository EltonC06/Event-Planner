package com.elton.eventplanner.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.elton.eventplanner.services.exceptions.InvalidEnumValueException;
import com.elton.eventplanner.services.exceptions.RoleNotAllowedException;
import com.elton.eventplanner.services.exceptions.WrongDateFormatException;

@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<Event> findAllEvent() {
		return eventRepository.findAll();
	}
	
	public EventDTO findEventById(Long id) {
		Event event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		EventDTO eventDTO = convertToDTO(event);
		return eventDTO;
	}
	
	public Event saveEvent(EventDTO eventDTO) {		
		Event event = convertToEntity(eventDTO);
		if (!userRepository.existsById(event.getUser().getId())) {
			throw new EntityNotFoundException(event.getUser().getId());
		}
		if (event.getUser().getRole().equals(UserRole.USER)) {
			throw new RoleNotAllowedException(event.getUser().getRole(), "create an event");
		} else {
			return eventRepository.save(event);
		}
	}
	
	public Event updateEvent(Long id, EventDTO eventDTO) {
		Event eventToUpdate = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		Event event = convertToEntity(eventDTO);
		if (!userRepository.existsById(event.getUser().getId())) {
			throw new EntityNotFoundException(event.getUser().getId());
		}
		if (event.getUser().getRole().equals(UserRole.USER)) {
			throw new RoleNotAllowedException(UserRole.USER, "update/manage an event");
		} else {
			eventToUpdate.setDate(event.getDate());
			eventToUpdate.setDescription(event.getDescription());
			eventToUpdate.setLocal(event.getLocal());
			eventToUpdate.setName(event.getName());
			eventToUpdate.setUser(event.getUser());
			eventToUpdate.setEventStatus(event.getEventStatus());
			return eventRepository.save(eventToUpdate);
		}
	}
	
	public void deleteEvent(Long id) {
		if (!eventRepository.existsById(id)) {
			throw new EntityNotFoundException(id);
		} else {
			eventRepository.deleteById(id);
		}
	}
	
	public Event cancelEvent(Long id) {
		Event event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		event.setEventStatus(EventStatus.CANCELLED);
		return eventRepository.save(event);
	}
	
	public Event autoStatusUpdate(Long id) {
		Event event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		Date calendar = Calendar.getInstance().getTime();
		if (event.getDate().before(calendar) && !event.getEventStatus().equals(EventStatus.CANCELLED)) {
			event.setEventStatus(EventStatus.COMPLETED);
			return eventRepository.save(event);
		} else {
			if (event.getDate().after(calendar) && !event.getEventStatus().equals(EventStatus.CANCELLED)) {
				event.setEventStatus(EventStatus.PLANNED);
				return eventRepository.save(event);
			}
		}
		return eventRepository.save(event);
	}
	
	private Event convertToEntity(EventDTO eventDTO) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Event eventConverted = new Event();
		if (!userRepository.existsById(eventDTO.getUserId())) {
			throw new EntityNotFoundException(eventDTO.getUserId());
		}
		try {
			eventConverted.setDate(sdf.parse(eventDTO.getDate()));
		} catch (ParseException e) {
			throw new WrongDateFormatException();
		}
		eventConverted.setDescription(eventDTO.getDescription());
		eventConverted.setLocal(eventDTO.getLocal());
		eventConverted.setName(eventDTO.getName());
		eventConverted.setUser(userRepository.findById(eventDTO.getUserId()).get());
		if (!eventDTO.getEventStatus().equals(EventStatus.PLANNED.toString()) &&
				!eventDTO.getEventStatus().equals(EventStatus.CANCELLED.toString()) &&
				!eventDTO.getEventStatus().equals(EventStatus.COMPLETED.toString())
				) {
			throw new InvalidEnumValueException("Please use only \"PLANNED\", \"CANCELLED\" or \"COMPLETED\"");
		}
		eventConverted.setEventStatus(EventStatus.valueOf(eventDTO.getEventStatus()));
		return eventConverted;
	}
	
	private EventDTO convertToDTO(Event event) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EventDTO eventDTO = new EventDTO();
		eventDTO.setName(event.getName());
		eventDTO.setDescription(event.getDescription());
		eventDTO.setLocal(event.getLocal());
		eventDTO.setDate(sdf.format(event.getDate()));
		eventDTO.setUserId(event.getUser().getId());
		eventDTO.setEventStatus(event.getEventStatus().toString());
		return eventDTO;
	}
}
