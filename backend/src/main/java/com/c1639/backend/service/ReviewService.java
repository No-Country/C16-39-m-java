package com.c1639.backend.service;

import com.c1639.backend.model.review.Review;
import com.c1639.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Review review){
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review updatedReview){
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setRate(updatedReview.getRate());
            return reviewRepository.save(review);
        } else {
            throw new NotFoundException("Review not found with id: " + id);
        }
    }

    public List<Review>getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review getById(Long id){
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            return reviewRepository.getReferenceById(id);
        } else {
            throw new NotFoundException("Review not found with id: " + id);
        }
    }
}
