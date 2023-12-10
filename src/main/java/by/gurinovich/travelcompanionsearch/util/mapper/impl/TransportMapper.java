package by.gurinovich.travelcompanionsearch.util.mapper.impl;

import by.gurinovich.travelcompanionsearch.dto.TransportDTO;
import by.gurinovich.travelcompanionsearch.model.Transport;
import by.gurinovich.travelcompanionsearch.util.mapper.Mappable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransportMapper implements Mappable<Transport, TransportDTO> {
    private final ModelMapper modelMapper;

    @Override
    public Transport fromDTO(TransportDTO dto) {
        return modelMapper.map(dto, Transport.class);
    }

    @Override
    public TransportDTO toDTO(Transport entity) {
        return modelMapper.map(entity, TransportDTO.class);
    }

    @Override
    public List<Transport> fromDTOs(List<TransportDTO> dtos) {
        return dtos.stream().map(this::fromDTO).toList();
    }

    @Override
    public List<TransportDTO> toDTOs(List<Transport> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
