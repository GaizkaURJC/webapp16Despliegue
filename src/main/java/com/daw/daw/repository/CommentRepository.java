package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.daw.daw.model.Comment;
import com.daw.daw.model.User;
import java.util.Collection;
import java.util.List;

/**
 * This file defines the CommentRepository interface, which is part of the
 * data access layer of the application. It is responsible for providing
 * CRUD operations for the Comment entity, allowing interaction with the
 * underlying database.
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUsername(String userName);

    Optional<Comment> findByUser(User user);

    Optional<Comment> findByComentario(String coment);

    Optional<Comment> findByRate(int rate);

    Optional<Comment> getComentsById(Long id);

    List<Comment> getComentsByEventId(Long eventId);

    List<Comment> getCommentsByRate(int rate);

    Collection<Comment> getCommentsByUserId(User user);

}