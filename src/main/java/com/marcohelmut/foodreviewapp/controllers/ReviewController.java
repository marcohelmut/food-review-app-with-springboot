package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.dtos.reviewdtos.CreateReviewDto;
import com.marcohelmut.foodreviewapp.dtos.reviewdtos.ReviewResponseDto;
import com.marcohelmut.foodreviewapp.entities.Review;
import com.marcohelmut.foodreviewapp.services.ReviewService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(StallController.class);

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@Valid @RequestBody CreateReviewDto review) {
        ReviewResponseDto savedReview = reviewService.saveReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @GetMapping("/foods/{id}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByFood(@PathVariable Long id) {
        List<ReviewResponseDto> reviews = reviewService.getReviewsByFood(id);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getReviewCount() {
        return ResponseEntity.ok(reviewService.getReviewCount());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        logger.info("Delete stall request received: {}", id);
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
