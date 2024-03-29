package by.gurinovich.travelcompanionsearch.controller;

import by.gurinovich.travelcompanionsearch.dto.PostResponseDTO;
import by.gurinovich.travelcompanionsearch.model.Post;
import by.gurinovich.travelcompanionsearch.model.PostResponse;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.service.MailService;
import by.gurinovich.travelcompanionsearch.service.PostResponseService;
import by.gurinovich.travelcompanionsearch.service.PostService;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.PostResponseMapper;
import by.gurinovich.travelcompanionsearch.util.validator.PostResponseDTOValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/responses")
@RequiredArgsConstructor
public class PostResponseController {

    private final PostResponseService postResponseService;
    private final PostResponseMapper postResponseMapper;
    private final UserService userService;
    private final PostService postService;
    private final MailService mailService;
    private final PostResponseDTOValidator postResponseDTOValidator;

    @GetMapping("/users/{user_id}")
    public ResponseEntity<List<PostResponseDTO>> getAllByUser(@PathVariable("user_id") UUID userId){
        User user = userService.getById(userId);
        return ResponseEntity.ok()
                .body(postResponseMapper.toDTOs(postResponseService.getAllByUser(user)));
    }

    @PostMapping("/users/{user_id}/{post_id}")
    public ResponseEntity<PostResponseDTO> respondPost(@PathVariable("user_id") UUID userId,
                                                       @PathVariable("post_id") UUID postId,
                                                       @RequestBody @Valid PostResponseDTO postResponseDTO,
                                                       BindingResult bindingResult){
        postResponseDTOValidator.validate(postResponseDTO, bindingResult);
        User user = userService.getById(userId);
        Post post = postService.getById(postId);
        PostResponse postResponse = postResponseMapper.fromDTO(postResponseDTO);
        postResponse.setUser(user);
        postResponse.setPost(post);
        postResponse = postResponseService.save(postResponse);
        mailService.sendResponseMessage(post, postResponse);
        return ResponseEntity.ok(postResponseMapper.toDTO(postResponse));
    }

    @DeleteMapping("/{response_id}")
    public HttpStatus deleteById(@PathVariable("response_id") Long id){
        postResponseService.deleteById(id);
        return HttpStatus.OK;
    }
}
