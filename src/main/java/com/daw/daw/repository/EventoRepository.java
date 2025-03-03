package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.daw.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Optional <Evento> findByNombre(String nombre);
    Optional <Evento> findByTipo(String tipo);
    Optional <Evento> findByDescripcion(String descripcion);
    Optional <Evento> getEventoById(Long id); 
}
