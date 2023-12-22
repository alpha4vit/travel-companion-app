package by.gurinovich.travelcompanionsearch.repository;

import by.gurinovich.travelcompanionsearch.model.PostResponse;
import by.gurinovich.travelcompanionsearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostResponseRepository extends JpaRepository<PostResponse, Long> {
    List<PostResponse> findAllByUser(User user);
}
