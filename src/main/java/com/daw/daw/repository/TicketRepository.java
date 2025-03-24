package com.daw.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.daw.daw.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserEmail(String userEmail);

    List<Ticket> findByTitle(String title);

    List<Ticket> findByUserOwner(String userOwner);

    List<Ticket> findByDni(String dni);

    long countByTitle(String title);

    List<Ticket> findByTitleAndGender(String title, String gender);

    List<Ticket> findByCategory(String category);

}
