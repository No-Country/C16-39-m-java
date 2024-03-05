package com.c1639.backend.controller;

import com.c1639.backend.dto.review.ListReviewsDTO;
import com.c1639.backend.dto.review.ReviewDTO;
import com.c1639.backend.model.comment.Comment;
import com.c1639.backend.model.review.Review;
import com.c1639.backend.repository.CommentRepository;
import com.c1639.backend.repository.ReviewRepository;
import com.c1639.backend.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Review", description = "Manage all endpoints about Reviews")
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor

public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Operation(
            summary = "Get all reviews",
            description = "Get all reviews from one movie"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "All reviews found succesfully",
                    content = {
                            @Content(mediaType = "aplication/json",
                                    schema = @Schema(implementation = ListReviewsDTO.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Cannot retrieve all reviews", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @GetMapping("/getAllReviews")
    public ResponseEntity<List<Review>> getAlReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @Operation(
            summary = "Create a review",
            description = "Add the review to the database"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Review created successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ListReviewsDTO.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Cannot create a review", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @PostMapping("/new")
    public Review createReview(Review review) {
        return reviewService.createReview(review);
    }

    @Operation(
            summary = "Update a review",
            description = "Change the rate of a review"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Review updated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewDTO.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Cannot update the review", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, Review updatedReview) {
        Review review = reviewService.updateReview(id, updatedReview);
        return ResponseEntity.ok(review);
    }

    @Operation(
            summary = "Get review",
            description = "Find a review using their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Review returned successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewDTO.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Cannot return the movie", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Not found", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content})
    })
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getById(id);
        return ResponseEntity.ok(review);
    }


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Operation(
            summary = "create a Coment",
            description = "The request body must contain the Comment object"
    )

    @PostMapping("/{reviewId}/comments")
    public ResponseEntity<?> addCommentToReview(@PathVariable Long reviewId, @RequestBody Comment comment) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (!optionalReview.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Review review = optionalReview.get();
        comment.setReview(review);
        review.setComment(comment);

        reviewRepository.save(review);

        return ResponseEntity.ok().build();
    }
}
