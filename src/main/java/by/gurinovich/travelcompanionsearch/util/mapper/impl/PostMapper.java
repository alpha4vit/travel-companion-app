package by.gurinovich.travelcompanionsearch.util.mapper.impl;

import by.gurinovich.travelcompanionsearch.dto.PostDTO;
import by.gurinovich.travelcompanionsearch.dto.TransportDTO;
import by.gurinovich.travelcompanionsearch.model.Post;
import by.gurinovich.travelcompanionsearch.service.TransportService;
import by.gurinovich.travelcompanionsearch.util.mapper.Mappable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper implements Mappable<Post, PostDTO> {

    private final ModelMapper modelMapper;
    private final TransportService transportService;

    @Override
    public Post fromDTO(PostDTO dto) {
        Post post = modelMapper.map(dto, Post.class);
        post.setTransport(transportService.getById(dto.getTransportId()));
        return post;
    }

    @Override
    public PostDTO toDTO(Post entity) {
        return modelMapper.map(entity, PostDTO.class);
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
