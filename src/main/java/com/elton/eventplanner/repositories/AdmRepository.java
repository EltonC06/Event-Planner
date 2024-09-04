package com.elton.eventplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elton.eventplanner.entities.Adm;

@Repository
public interface AdmRepository extends JpaRepository<Adm, Long> {

}
