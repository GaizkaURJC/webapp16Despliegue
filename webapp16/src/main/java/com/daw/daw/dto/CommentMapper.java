package com.daw.daw.dto;

import org.mapstruct.Mapper;

import com.daw.daw.model.Comment;
import org.mapstruct.Mapping;
import java.util.Collection;

import java.util.List;

/**
 * This file defines the CommentMapper class, which is responsible for mapping
 * Comment entities to Data Transfer Objects (DTOs) and vice versa. It is part
 * of the web application project for the 2024-2025 academic year.
 */

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDTO toDTO(Comment coments);

    Comment toDTOs(CommentDTO commentDTO);

    List<CommentDTO> toDTOs(Collection<Comment> comments);

    @Mapping(target = "id", ignore = true)
    Comment toDomain(CommentDTO commentDTO);

}
