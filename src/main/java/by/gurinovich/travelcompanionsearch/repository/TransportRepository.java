package by.gurinovich.travelcompanionsearch.repository;

import by.gurinovich.travelcompanionsearch.dto.TransportDTO;
import by.gurinovich.travelcompanionsearch.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
}
