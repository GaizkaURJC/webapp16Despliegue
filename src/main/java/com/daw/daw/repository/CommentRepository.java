package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.daw.daw.model.Comment;
import com.daw.daw.model.User;
import java.util.Collection;
import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUsername(String userName); // Cambiar a findByUsername
    Optional <Comment> findByUser(User user);
    Optional<Comment> findByComentario(String coment); // Cambiar a findByComentario
    Optional<Comment> findByRate(int rate); // Cambiar a findByValoracion
    Optional <Comment> getComentsById(Long id);
    List <Comment> getComentsByEventId(Long eventId);
    List <Comment> getCommentsByRate(int rate);
    Collection <Comment> getCommentsByUserId(User user);

    
}