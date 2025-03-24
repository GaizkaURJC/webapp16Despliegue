package com.daw.daw.service;

import java.util.Collection;
import java.util.NoSuchElementException;

import com.daw.daw.security.CSRFHandlerConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import com.daw.daw.model.Comment;
import com.daw.daw.model.User;
import com.daw.daw.dto.*;
import com.daw.daw.repository.CommentRepository;
import com.daw.daw.repository.UserRepository;

/**
 * This service class provides methods to manage comments in the application.
 * It includes functionalities to create, retrieve, update, and delete comments.
 * Additionally, it allows fetching comments based on specific criteria such as
 * user ID or rating.
 * The class uses various Spring components and repositories to perform these
 * operations.
 */

@Service
public class CommentService {

    private final UserRepository userRepository;

    private final SecurityFilterChain apiFilterChain;

    private final DaoAuthenticationProvider authenticationProvider;

    private final PasswordEncoder passwordEncoder;

    private final CreateUserMapperImpl createUserMapperImpl;

    private final AuthenticationManager authenticationManager;

    private final CSRFHandlerConfiguration CSRFHandlerConfiguration;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    private Comment toDomain(CommentDTO commentDTO) {
        return commentMapper.toDomain(commentDTO);
    }

    CommentService(CSRFHandlerConfiguration CSRFHandlerConfiguration, AuthenticationManager authenticationManager,
            CreateUserMapperImpl createUserMapperImpl, PasswordEncoder passwordEncoder,
            DaoAuthenticationProvider authenticationProvider, SecurityFilterChain apiFilterChain,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.CSRFHandlerConfiguration = CSRFHandlerConfiguration;
        this.createUserMapperImpl = createUserMapperImpl;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.apiFilterChain = apiFilterChain;
        this.userRepository = userRepository;
    }

    private Collection<CommentDTO> toDTOs(Collection<Comment> comments) {
        return commentMapper.toDTOs(comments);
    }

    public Collection<CommentDTO> getAllComments() {

        return commentMapper.toDTOs(commentRepository.findAll());
    }

    public CommentDTO getCommentById(long id) {
        return commentMapper.toDTO(commentRepository.findById(id).orElseThrow());
    }

    public Collection<CommentDTO> getCommentByRate(int rate) {
        return commentMapper.toDTOs(commentRepository.getCommentsByRate(rate));
    }

    public Collection<CommentDTO> getCommentsByUserId(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        return commentMapper.toDTOs(commentRepository.findByUser(user).stream().toList());
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        if (commentDTO.id() != null) {
            throw new IllegalArgumentException("id must be null");
        }
        Comment comment = toDomain(commentDTO);
        comment = commentRepository.save(comment);

        return commentMapper.toDTO(comment);
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    public Collection<CommentDTO> deleteAllComments() {
        Collection<CommentDTO> allComments = commentMapper.toDTOs(commentRepository.findAll());
        commentRepository.deleteAll();
        return allComments;
    }

    public void replaceComment(long id, CommentDTO updateCommentDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment = toDomain(updateCommentDTO);
        commentRepository.save(comment);
    }

}
