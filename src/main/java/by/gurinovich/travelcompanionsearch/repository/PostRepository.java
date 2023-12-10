package by.gurinovich.travelcompanionsearch.repository;

import by.gurinovich.travelcompanionsearch.dto.PostDTO;
import by.gurinovich.travelcompanionsearch.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
}
