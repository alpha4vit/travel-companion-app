package by.gurinovich.travelcompanionsearch.util.mapper.impl;


import by.gurinovich.travelcompanionsearch.dto.ImageDTO;
import by.gurinovich.travelcompanionsearch.model.Image;
import by.gurinovich.travelcompanionsearch.util.mapper.Mappable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ImageMapper implements Mappable<Image, ImageDTO> {
    private final ModelMapper modelMapper;


    @Override
    public Image fromDTO(ImageDTO dto) {
        return modelMapper.map(dto, Image.class);
    }

    @Override
    public ImageDTO toDTO(Image entity) {
        return modelMapper.map(entity, ImageDTO.class);
    }

    @Override
    public List<Image> fromDTOs(List<ImageDTO> dtos) {
        return dtos.stream().map(this::fromDTO).toList();
    }

    @Override
    public List<ImageDTO> toDTOs(List<Image> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}