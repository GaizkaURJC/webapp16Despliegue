package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.daw.daw.model.Booking;

/**
 * This interface represents the repository for managing Booking entities.
 * It extends JpaRepository to provide CRUD operations and additional query
 * methods
 * for retrieving Booking entities based on specific attributes such as
 * userName, userEmail, and bussinesName.
 * The repository is annotated with @Repository to indicate that it's a Spring
 * Data repository.
 */

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByUserName(String userName);

    Optional<Booking> findByUserEmail(String userEmail);

    Optional<Booking> findByBussinesName(String bussinesName);

    Optional<Booking> getBookingById(Long id);
}