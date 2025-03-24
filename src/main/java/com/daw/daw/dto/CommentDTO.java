package com.daw.daw.dto;


public record CommentDTO( 

    Long id,
    String username,
    String comentario,
    int eventId,
    int rate){
        public Long getId() {
            return id;
        }
    
    
}
