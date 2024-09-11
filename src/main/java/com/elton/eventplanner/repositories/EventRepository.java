package com.elton.eventplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elton.eventplanner.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
