package by.gurinovich.travelcompanionsearch.service;

import by.gurinovich.travelcompanionsearch.exception.ResourceNotFoundException;
import by.gurinovich.travelcompanionsearch.model.PostResponse;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.repository.PostResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostResponseService {
    private final PostResponseRepository postResponseRepository;

    public List<PostResponse> getAllByUser(User user){
        return postResponseRepository.findAllByUser(user);
    }

    public PostResponse getById(Long id){
        return postResponseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Response with this id not found!"));
    }

    @Transactional
    public PostResponse save(PostResponse postResponse){
        postResponse.setCreationDate(Instant.now());
        return postResponseRepository.save(postResponse);
    }

    @Transactional
    public void deleteById(Long id){
        getById(id);
        postResponseRepository.deleteById(id);
    }

}
