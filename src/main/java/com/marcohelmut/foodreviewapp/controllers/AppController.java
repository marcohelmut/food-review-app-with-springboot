package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.entities.Review;
import com.marcohelmut.foodreviewapp.entities.Stall;
import com.marcohelmut.foodreviewapp.services.AppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping("/stalls")
    public ResponseEntity<Stall> createStall(@Valid @RequestBody Stall stall) {
        Stall savedStall = appService.saveStall(stall);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStall);
    }

    @PostMapping("/foods")
    public ResponseEntity<Food> createFood(@Valid @RequestBody Food food) {
        Food savedFood = appService.saveFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        Review savedReview = appService.saveReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }
}
