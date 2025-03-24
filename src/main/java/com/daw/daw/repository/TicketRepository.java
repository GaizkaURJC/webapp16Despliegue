package com.daw.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.daw.model.Ticket;

import java.util.List;

/**
 * TicketRepository is an interface that extends JpaRepository to provide CRUD
 * operations
 * and custom query methods for the Ticket entity. This repository interface
 * allows
 * interaction with the database to perform operations such as finding tickets
 * by
 * user email, title, user owner, DNI, and category. It also includes methods to
 * count
 * tickets by title and find tickets by a combination of title and gender.
 */

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserEmail(String userEmail);

    List<Ticket> findByTitle(String title);

    List<Ticket> findByUserOwner(String userOwner);

    List<Ticket> findByDni(String dni);

    long countByTitle(String title);

    List<Ticket> findByTitleAndGender(String title, String gender);

    List<Ticket> findByCategory(String category);

    long countByGender(String gender);

}
