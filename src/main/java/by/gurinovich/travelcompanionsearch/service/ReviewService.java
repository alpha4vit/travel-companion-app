package by.gurinovich.travelcompanionsearch.service;

import by.gurinovich.travelcompanionsearch.model.Review;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;


    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    public List<Review> getAllByUser(User user){
        return reviewRepository.findAllByUser(user);
    }

    @Transactional
    public Review save(Review review){
        User user = review.getUser();
        user.setRating(updateUserRating(user, review));
        review.setCreationDate(Instant.now());
        return reviewRepository.save(review);
    }

    private Double updateUserRating(User user, Review review){
        long reviewCount = user.getReviews().size();

        return (((double)reviewCount*user.getRating()+review.getStars())/(reviewCount+1d));
    }

}
