package com.daw.daw.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.daw.model.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional <Ticket> findByTitulo(String titulo);
    Optional <Ticket> getEntradaById(Long id);
    List<Ticket> findAll();
    List<Ticket> findByUserOwner(String userOwner);
    List<Ticket> findByPrecio(int precio);

    
} 