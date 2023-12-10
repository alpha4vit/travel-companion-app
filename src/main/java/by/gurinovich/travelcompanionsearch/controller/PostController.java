package by.gurinovich.travelcompanionsearch.controller;

import by.gurinovich.travelcompanionsearch.dto.PostDTO;
import by.gurinovich.travelcompanionsearch.model.Post;
import by.gurinovich.travelcompanionsearch.service.PostService;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll(){
        return new ResponseEntity<>(
                postMapper.toDTOs(postService.getAll()),
                HttpStatus.OK);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<PostDTO> getById(@PathVariable("post_id") UUID postId){
        return new ResponseEntity<>(
                postMapper.toDTO(postService.getById(postId)),
                HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<PostDTO>> getAllByUser(@PathVariable("user_id") UUID userId){
        return new ResponseEntity<>(
                postMapper.toDTOs(userService.getById(userId).getPosts()),
                HttpStatus.OK
        );
    }

    @PostMapping("/{user_id}/create")
    public ResponseEntity<PostDTO> createPost(@PathVariable("user_id") UUID userId,
                                                    @RequestBody PostDTO postDTO){
        Post post = postMapper.fromDTO(postDTO);
        post.setUser(userService.getById(userId));
        post = postService.save(post);
        return new ResponseEntity<>(
                postMapper.toDTO(post),
                HttpStatus.OK
        );
    }

}
