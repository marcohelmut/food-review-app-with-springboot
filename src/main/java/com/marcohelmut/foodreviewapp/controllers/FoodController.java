package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.dtos.fooddtos.CreateFoodDto;
import com.marcohelmut.foodreviewapp.dtos.fooddtos.FoodResponseDto;
import com.marcohelmut.foodreviewapp.services.FoodService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<FoodResponseDto> createFood(@Valid @RequestBody CreateFoodDto food) {
        logger.info("Create food request received: {}", food.name());
        FoodResponseDto savedFood = foodService.saveFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
    }

    @GetMapping("/stall/{stallId}")
    public ResponseEntity<List<FoodResponseDto>> getFoodsByStall(@PathVariable Long stallId) {
        List<FoodResponseDto> list = foodService.getFoodsByStall(stallId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> getFoodById(@PathVariable Long id) {
        FoodResponseDto food = foodService.getFoodById(id);
        return ResponseEntity.ok(food);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        logger.info("Delete food request received with id: {}", id);
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalFoodCount() {
        long count = foodService.getTotalFoodCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/rankings/price")
    public ResponseEntity<List<FoodResponseDto>> getPriceRanking() {
        return ResponseEntity.ok(foodService.getPriceRanking());
    }

    @GetMapping("/rankings/taste")
    public ResponseEntity<List<FoodResponseDto>> getTasteRanking() {
        return ResponseEntity.ok(foodService.getTasteRanking());
    }

    @GetMapping("/rankings/cleanliness")
    public ResponseEntity<List<FoodResponseDto>> getCleanlinessRanking() {
        return ResponseEntity.ok(foodService.getCleanlinessRanking());
    }

}
