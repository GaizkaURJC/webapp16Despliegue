package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.daw.model.Image;

/**
 * This interface represents the repository for managing Image entities.
 * It extends JpaRepository to provide CRUD operations and additional
 * query methods for the Image entity.
 * 
 * The repository is annotated with @Repository to indicate that it is a
 * Spring Data repository.
 */

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByTitle(String title);

}