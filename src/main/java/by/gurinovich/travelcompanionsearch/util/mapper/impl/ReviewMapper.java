package by.gurinovich.travelcompanionsearch.util.mapper.impl;

import by.gurinovich.travelcompanionsearch.dto.ReviewDTO;
import by.gurinovich.travelcompanionsearch.model.Review;
import by.gurinovich.travelcompanionsearch.util.mapper.Mappable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewMapper implements Mappable<Review, ReviewDTO> {

    private final ModelMapper modelMapper;

    @Override
    public Review fromDTO(ReviewDTO dto) {
        return modelMapper.map(dto, Review.class);
    }

    @Override
    public ReviewDTO toDTO(Review entity) {
        return modelMapper.map(entity, ReviewDTO.class);
    }

    @Override
    public List<Review> fromDTOs(List<ReviewDTO> dtos) {
        return dtos.stream().map(this::fromDTO).toList();
    }

    @Override
    public List<ReviewDTO> toDTOs(List<Review> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
