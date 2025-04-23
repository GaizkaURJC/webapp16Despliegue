package com.daw.daw.controller.API;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.daw.dto.CommentDTO;
import com.daw.daw.security.CSRFHandlerConfiguration;
import com.daw.daw.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * This class is a REST controller for handling API requests related to
 * comments.
 * It provides endpoints for creating, retrieving, updating, and deleting
 * comments.
 * The controller is part of the web application for the "Desarrollo de
 * aplicaciones web" course.
 */

@RestController
@RequestMapping("/api/v1/comments")
public class CommentRestController {

    private final AuthenticationManager authenticationManager;

    private final SecurityFilterChain apiFilterChain;

    private final CSRFHandlerConfiguration csrfHandlerConfiguration;

    @Autowired
    private CommentService commentService;

    

    CommentRestController(CSRFHandlerConfiguration csrfHandlerConfiguration, SecurityFilterChain apiFilterChain,
            AuthenticationManager authenticationManager) {
        this.csrfHandlerConfiguration = csrfHandlerConfiguration;
        this.apiFilterChain = apiFilterChain;
        this.authenticationManager = authenticationManager;
    }

    @Operation(summary = "Get all comments")
    @GetMapping("/")
    public Collection<CommentDTO> getComments() {

        return commentService.getAllComments();
    }

    @Operation(summary  = "Get a comment by its id")
    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/rate/{rate}")
    public Collection<CommentDTO> getCommentsByRate(@PathVariable int rate) {
        return commentService.getCommentByRate(rate);
    }

    @GetMapping("/user/{userId}")
    public Collection<CommentDTO> getCommentByUserId(@PathVariable Long userId) {

        return commentService.getCommentsByUserId(userId);
    }

    @Operation(summary = "Create a new comment")
    @PostMapping("/")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {

        commentDTO = commentService.createComment(commentDTO);

        URI location = fromCurrentRequest().path("/{id}")
                .buildAndExpand(commentDTO.getId()).toUri();

        return ResponseEntity.created(location).body(commentDTO);
    }

    @Operation (summary = "Delete a comment by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Delete all comments")
    @DeleteMapping("/")
    public Collection<CommentDTO> deleteAllComments() {
        return commentService.deleteAllComments();
    }

    @Operation(summary = "Update a comment by its id")
    @PutMapping("/{id}")
    public void replaceComment(@PathVariable Long id, @RequestBody CommentDTO updateCommentDTO) {
        commentService.replaceComment(id, updateCommentDTO);
    }

}
