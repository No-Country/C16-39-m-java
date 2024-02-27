package com.c1639.backend.service;

import com.c1639.backend.model.review.Review;
import com.c1639.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Review review){
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review updatedReview){
        Review review = reviewRepository.getReferenceById(id);
        review.setRate(updatedReview.getRate());
        return reviewRepository.save(review);
    }

    public List<Review>getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review getById(Long id){
        return reviewRepository.getReferenceById(id);
    }
}
