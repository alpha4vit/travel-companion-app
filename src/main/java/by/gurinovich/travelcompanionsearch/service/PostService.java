package by.gurinovich.travelcompanionsearch.service;

import by.gurinovich.travelcompanionsearch.dto.PostDTO;
import by.gurinovich.travelcompanionsearch.exception.ResourceNotFoundException;
import by.gurinovich.travelcompanionsearch.model.Post;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getPage(Integer limit, Integer page){
        Pageable pageable = PageRequest.of(page-1, limit);
        return postRepository.findAll(pageable).getContent();
    }

    public Long getAllCount(){
        return postRepository.count();
    }

    public Post save(Post entity) {
        setRandomUUID(entity);
        return postRepository.save(entity);
    }

    public Post getById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with this id not found!"));
    }

    public void deleteById(UUID uuid) {
        postRepository.deleteById(uuid);
    }

    private void setRandomUUID(Post post){
        UUID uuid = UUID.randomUUID();
        while (postRepository.findById(uuid).isPresent())
            uuid = UUID.randomUUID();
        post.setId(uuid);
    }
}
