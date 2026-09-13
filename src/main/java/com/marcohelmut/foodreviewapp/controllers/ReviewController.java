package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.entities.Review;
import com.marcohelmut.foodreviewapp.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }
}
