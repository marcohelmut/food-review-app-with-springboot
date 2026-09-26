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

    @GetMapping("/{id}")
    public ResponseEntity<List<FoodResponseDto>> getFoodsByStall(@PathVariable Long id) {
        List<FoodResponseDto> list = foodService.getFoodsByStall(id);
        return ResponseEntity.ok(list);
    }

}
