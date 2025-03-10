package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.daw.daw.model.Coments;
import java.util.List;
@Repository
public interface ComentsRepository extends JpaRepository<Coments, Long> {

    Optional<Coments> findByUsername(String userName); // Cambiar a findByUsername
    Optional<Coments> findByComentario(String coment); // Cambiar a findByComentario
    Optional<Coments> findByValoracion(int rate); // Cambiar a findByValoracion
    Optional <Coments> getComentsById(Long id);
    List <Coments> getComentsByEventId(Long eventId);
    
}