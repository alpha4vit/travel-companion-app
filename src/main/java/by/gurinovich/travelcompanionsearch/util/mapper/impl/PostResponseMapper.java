package by.gurinovich.travelcompanionsearch.util.mapper.impl;

import by.gurinovich.travelcompanionsearch.dto.PostResponseDTO;
import by.gurinovich.travelcompanionsearch.model.PostResponse;
import by.gurinovich.travelcompanionsearch.util.mapper.Mappable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostResponseMapper implements Mappable<PostResponse, PostResponseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public PostResponse fromDTO(PostResponseDTO dto) {
        return modelMapper.map(dto, PostResponse.class);
    }

    @Override
    public PostResponseDTO toDTO(PostResponse entity) {
        return modelMapper.map(entity, PostResponseDTO.class);
    }

    @Override
    public List<PostResponse> fromDTOs(List<PostResponseDTO> dtos) {
        return dtos.stream().map(this::fromDTO).toList();
    }

    @Override
    public List<PostResponseDTO> toDTOs(List<PostResponse> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
