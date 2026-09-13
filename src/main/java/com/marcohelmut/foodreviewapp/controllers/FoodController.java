package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import com.marcohelmut.foodreviewapp.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodRepository foodRepository, FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@Valid @RequestBody Food food) {
        Food savedFood = foodService.saveFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
    }

}
