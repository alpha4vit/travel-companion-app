package by.gurinovich.travelcompanionsearch.service;

import by.gurinovich.travelcompanionsearch.exception.ResourceNotFoundException;
import by.gurinovich.travelcompanionsearch.model.Transport;
import by.gurinovich.travelcompanionsearch.repository.TransportRepository;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.TransportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransportService {

    private final TransportRepository transportRepository;


    @Transactional
    public void deleteById(Long id){
        getById(id);
        transportRepository.deleteById(id);
    }

    public Transport getById(Long id){
        return transportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transport with this id not found!"));
    }

    public List<Transport> getAll(){
        return transportRepository.findAll();
    }

    @Transactional
    public Transport save(Transport transport){
        return transportRepository.save(transport);
    }
}
