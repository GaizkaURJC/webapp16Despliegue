package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.daw.daw.model.Booking;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional <Booking> findByUserName(String userName);
    Optional <Booking> findByUserEmail(String userEmail);
    Optional <Booking> findByBussinesName(String bussinesName);
    Optional <Booking> getBookingById(Long id);
} 