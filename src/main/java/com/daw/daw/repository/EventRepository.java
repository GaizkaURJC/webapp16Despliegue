package com.daw.daw.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.daw.model.Event;

/**
 * This file defines the EventRepository interface which is part of the
 * data access layer of the web application. It is responsible for
 * providing CRUD operations and custom queries for Event entities
 * in the database.
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByTitle(String title);

    List<Event> findByType(String type);

    Optional<Event> findByDescription(String description);

    Optional<Event> getEventoById(Long id);

    Optional<Event> getByTitle(String Title);

    Optional<Event> getByType(String type);

    List<Event> findAllByType(String type);

    boolean existsByTitle(String title);

    Optional<Event> findByCategory(String category);

}
