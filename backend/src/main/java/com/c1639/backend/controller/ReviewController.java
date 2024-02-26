package com.c1639.backend.controller;

import com.c1639.backend.dto.review.ListReviewsDTO;
import com.c1639.backend.model.review.Review;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                    responseCode= "200", description = "All reviews found succesfully",
                    content = {
                            @Content(mediaType = "aplication/json",
                                    schema = @Schema(implementation = ListReviewsDTO.class))
                    }
            )
            @ApiResponse(responseCode = "400", description = "Cannot retrieve all reviews", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @GetMapping("/getAllReviews")
    public ResponseEntity<List<Review>> getAlReviews(){
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity.ok(reviews);
    }
}
