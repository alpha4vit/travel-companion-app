package by.gurinovich.travelcompanionsearch.repository;

import by.gurinovich.travelcompanionsearch.model.Review;
import by.gurinovich.travelcompanionsearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUser(User user);
}
