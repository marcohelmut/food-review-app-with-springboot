package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.entities.Review;
import com.marcohelmut.foodreviewapp.entities.Stall;
import com.marcohelmut.foodreviewapp.services.FoodService;
import com.marcohelmut.foodreviewapp.services.ReviewService;
import com.marcohelmut.foodreviewapp.services.StallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

    private final StallService stallService;
    private final FoodService foodService;
    private final ReviewService reviewService;

    @Autowired
    public AppController(StallService stallService, FoodService foodService, ReviewService reviewService) {
        this.stallService = stallService;
        this.foodService = foodService;
        this.reviewService = reviewService;
    }

    @PostMapping("/stalls")
    public ResponseEntity<Stall> createStall(@Valid @RequestBody Stall stall) {
        Stall savedStall = stallService.saveStall(stall);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStall);
    }

    @PostMapping("/foods")
    public ResponseEntity<Food> createFood(@Valid @RequestBody Food food) {
        Food savedFood = foodService.saveFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @GetMapping("/stalls/{name}")
    public ResponseEntity<Stall> getStallByName(@PathVariable String name) {
        Stall stall = stallService.getStallByName(name);
        return ResponseEntity.ok(stall);
    }
}
