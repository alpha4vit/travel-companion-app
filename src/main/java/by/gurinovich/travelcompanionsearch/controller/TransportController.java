package by.gurinovich.travelcompanionsearch.controller;

import by.gurinovich.travelcompanionsearch.dto.TransportDTO;
import by.gurinovich.travelcompanionsearch.model.Transport;
import by.gurinovich.travelcompanionsearch.service.TransportService;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.TransportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class TransportController {

    private final TransportMapper transportMapper;
    private final TransportService transportService;
    private final UserService userService;

    @GetMapping("/{user_id}/transport")
    public ResponseEntity<List<TransportDTO>> getAllUserTransport(@PathVariable("user_id") UUID userId){
        return new ResponseEntity<>(transportMapper.toDTOs(transportService.getAll()), HttpStatus.OK);
    }


    //TODO check if user authenticated
    //TODO create validation
    @PostMapping("/{user_id}/transport/add")
    public ResponseEntity<TransportDTO> addTransport(@PathVariable("user_id") UUID userId,
                                                     @RequestBody TransportDTO transportDTO){
        Transport transport = transportMapper.fromDTO(transportDTO);
        transport.setUser(userService.getById(userId));
        transport = transportService.save(transport);
        return new ResponseEntity<>(transportMapper.toDTO(transport), HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}/transport/{transport_id}")
    public ResponseEntity<TransportDTO> getTransportById(@PathVariable("transport_id") Long transportId){
        return new ResponseEntity<>(transportMapper.toDTO(transportService.getById(transportId)), HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}/transport/{transport_id}/delete")
    public HttpStatus deleteTransportById(@PathVariable("transport_id") Long transportId){
        transportService.deleteById(transportId);
        return HttpStatus.OK;
    }
}
