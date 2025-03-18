package com.daw.daw.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.daw.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional <Event> findByTitle(String title);
    List <Event> findByType(String type);
    Optional <Event> findByDescription(String description);
    Optional <Event> getEventoById(Long id); 
    Optional <Event> getByTitle(String Title);
    Optional <Event> getByType(String type);
    List <Event> findAllByType(String type);
    boolean existsByTitle(String title);
    Optional <Event> findByCategory(String category);

}
