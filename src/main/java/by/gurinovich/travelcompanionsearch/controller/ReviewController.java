package by.gurinovich.travelcompanionsearch.controller;

import by.gurinovich.travelcompanionsearch.dto.ReviewDTO;
import by.gurinovich.travelcompanionsearch.model.Review;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.service.ReviewService;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final UserService userService;

    @GetMapping("/users/{user_id}")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsForUser(@PathVariable("user_id") UUID userId){
        User user = userService.getById(userId);
        return ResponseEntity.ok(reviewMapper.toDTOs(reviewService.getAllByUser(user)));
    }

    @PostMapping("users/{user_id}/{creator_id}")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable("user_id") UUID userId,
                                                  @PathVariable("creator_id") UUID creatorId,
                                                  @RequestBody ReviewDTO reviewDTO){
        Review review = reviewMapper.fromDTO(reviewDTO);
        review.setUser(userService.getById(userId));
        review.setCreator(userService.getById(creatorId));
        review = reviewService.save(review);
        return ResponseEntity.ok(reviewMapper.toDTO(review));
    }
}
