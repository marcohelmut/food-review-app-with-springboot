package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.dtos.fooddtos.CreateFoodDto;
import com.marcohelmut.foodreviewapp.dtos.fooddtos.FoodResponseDto;
import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import com.marcohelmut.foodreviewapp.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodRepository foodRepository, FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<FoodResponseDto> createFood(@Valid @RequestBody CreateFoodDto food) {
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
    public ResponseEntity<Void> deleteFoodById(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalFoodCount() {
        long count = foodService.getTotalFoodCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/priceRank")
    public ResponseEntity<List<FoodResponseDto>> getPriceRanking() {
        return ResponseEntity.ok(foodService.getPriceRanking());
    }

    @GetMapping("/tasteRank")
    public ResponseEntity<List<FoodResponseDto>> getTasteRanking() {
        return ResponseEntity.ok(foodService.getTasteRanking());
    }

    @GetMapping("/cleanlinessRank")
    public ResponseEntity<List<FoodResponseDto>> getCleanlinessRanking() {
        return ResponseEntity.ok(foodService.getCleanlinessRanking());
    }

}
