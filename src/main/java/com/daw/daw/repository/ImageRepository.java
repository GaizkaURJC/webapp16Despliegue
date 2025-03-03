package com.daw.daw.repository;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.daw.model.Event;
import com.daw.daw.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional <Image> findByTitle(String title);

} 