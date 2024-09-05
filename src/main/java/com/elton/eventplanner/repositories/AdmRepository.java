package com.elton.eventplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elton.eventplanner.entities.User;

@Repository
public interface AdmRepository extends JpaRepository<User, Long> {

}
