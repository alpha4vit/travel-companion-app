package by.gurinovich.travelcompanionsearch.util.mapper.impl;

import by.gurinovich.travelcompanionsearch.dto.PostDTO;
import by.gurinovich.travelcompanionsearch.model.Post;
import by.gurinovich.travelcompanionsearch.service.TransportService;
import by.gurinovich.travelcompanionsearch.util.mapper.DateMapper;
import by.gurinovich.travelcompanionsearch.util.mapper.Mappable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper implements Mappable<Post, PostDTO> {

    private final ModelMapper modelMapper;
    private final TransportService transportService;
    private final UserMapper userMapper;

    @Override
    public Post fromDTO(PostDTO dto) {
        Post post = modelMapper.map(dto, Post.class);
        post.setDateBack(Instant.parse(dto.getDateBack()));
        post.setDateThere(Instant.parse(dto.getDateThere()));
        return post;
    }

    @Override
    public PostDTO toDTO(Post entity) {
        PostDTO dto = modelMapper.map(entity, PostDTO.class);
        dto.setUser(userMapper.toDTO(entity.getUser()));
        dto.setResponsesCount(entity.getResponses().size());
        return dto;
    }

    @Override
    public List<Post> fromDTOs(List<PostDTO> dtos) {
        return dtos.stream().map(this::fromDTO).toList();
    }

    @Override
    public List<PostDTO> toDTOs(List<Post> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
