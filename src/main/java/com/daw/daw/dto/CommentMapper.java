package com.daw.daw.dto;

import org.mapstruct.Mapper;

import com.daw.daw.model.Comment;
import org.mapstruct.Mapping;
import java.util.Collection;

import java.util.List;

@Mapper (componentModel = "spring")
public interface CommentMapper{

    CommentDTO toDTO (Comment coments);

    Comment toDTOs(CommentDTO commentDTO);
    
    List <CommentDTO> toDTOs(Collection <Comment> comments);

    @Mapping(target = "id", ignore = true)
    Comment toDomain(CommentDTO commentDTO);
    
    
}

