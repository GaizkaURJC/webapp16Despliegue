package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.daw.daw.model.Reserva;
import java.util.List;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Optional <Reserva> findByUserName(String userName);
    Optional <Reserva> findByUserEmail(String userEmail);
    Optional <Reserva> findByBussinesName(String bussinesName);
    Optional <Reserva> getReservaById(Long id);
} 