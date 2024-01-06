package by.gurinovich.travelcompanionsearch.controller;

import by.gurinovich.travelcompanionsearch.dto.PostDTO;
import by.gurinovich.travelcompanionsearch.dto.PostResponseDTO;
import by.gurinovich.travelcompanionsearch.model.Post;
import by.gurinovich.travelcompanionsearch.model.PostResponse;
import by.gurinovich.travelcompanionsearch.response.ResponseBody;
import by.gurinovich.travelcompanionsearch.service.PostResponseService;
import by.gurinovich.travelcompanionsearch.service.PostService;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.PostMapper;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.PostResponseMapper;
import by.gurinovich.travelcompanionsearch.util.validator.PostDTOValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;
    private final PostResponseMapper postResponseMapper;
    private final PostResponseService postResponseService;
    private final PostDTOValidator postDTOValidator;

    @GetMapping("")
    public ResponseEntity<List<PostDTO>> getAll(){
        return ResponseEntity.ok()
                .body(postMapper.toDTOs(postService.getAll()));
    }

    @GetMapping("/pages")
    public ResponseEntity<ResponseBody> getAll(
            @RequestParam("limit") Integer limit,
            @RequestParam("page") Integer page
    ){
        List<PostDTO> res = postMapper.toDTOs(postService.getPage(limit, page));
        Map<String, String> headers = new HashMap<>();
        headers.put("total_count", postService.getAllCount().toString());
        return ResponseEntity.ok()
                .body(new ResponseBody(res, headers));
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<PostDTO> getById(@PathVariable("post_id") UUID postId){
        return new ResponseEntity<>(
                postMapper.toDTO(postService.getById(postId)),
                HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<PostDTO>> getAllByUser(@PathVariable("user_id") UUID userId){
        return new ResponseEntity<>(
                postMapper.toDTOs(userService.getById(userId).getPosts()),
                HttpStatus.OK
        );
    }

    @PostMapping("/{user_id}/create")
    public ResponseEntity<PostDTO> createPost(@PathVariable("user_id") UUID userId,
                                              @RequestBody @Valid PostDTO postDTO, BindingResult bindingResult){
        postDTOValidator.validate(postDTO, bindingResult);
        Post post = postMapper.fromDTO(postDTO);
        post.setUser(userService.getById(userId));
        post = postService.save(post);
        return new ResponseEntity<>(
                postMapper.toDTO(post),
                HttpStatus.OK
        );
    }


    @PostMapping("/{post_id}/respond/{user_id}")
    public ResponseEntity<PostResponseDTO> respondPost(@PathVariable("post_id") UUID postId,
                                                       @PathVariable("user_id") UUID userId,
                                                       @RequestBody PostResponseDTO postResponseDTO){
        PostResponse postResponse = postResponseMapper.fromDTO(postResponseDTO);
        postResponse.setPost(postService.getById(postId));
        postResponse.setUser(userService.getById(userId));
        postResponse = postResponseService.save(postResponse);
        return ResponseEntity.ok()
                .body(postResponseMapper.toDTO(postResponse));
    }

    @DeleteMapping("/{post_id}")
    public HttpStatus deleteByPostId(@PathVariable("post_id") UUID postId){
        postService.deleteById(postId);
        return HttpStatus.OK;
    }

    @PatchMapping("/{post_id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("post_id") UUID postId, @RequestBody PostDTO postDTO){
        Post post = postService.update(postMapper.fromDTO(postDTO));
        return ResponseEntity.ok(postMapper.toDTO(post));
    }

}
