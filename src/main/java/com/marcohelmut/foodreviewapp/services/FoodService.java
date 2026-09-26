package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.dtos.fooddtos.CreateFoodDto;
import com.marcohelmut.foodreviewapp.dtos.fooddtos.FoodResponseDto;
import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.entities.Stall;
import com.marcohelmut.foodreviewapp.exceptions.foodexceptions.FoodNotFoundException;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import com.marcohelmut.foodreviewapp.repositories.StallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final StallRepository stallRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, StallRepository stallRepository) {
        this.foodRepository = foodRepository;
        this.stallRepository = stallRepository;
    }

    public FoodResponseDto saveFood(CreateFoodDto dto) {
        Stall stall = stallRepository.getReferenceById(dto.stallId());

        Food food = new Food();
        food.setName(dto.name());
        food.setPrice(dto.price());
        food.setStall(stall);
        food.setPhotoFilePath(dto.foodPhotoFilePath());
        food.setCreatedAt(Instant.now());

        Food savedFood = foodRepository.save(food);

        return new FoodResponseDto(
                savedFood.getId(),
                savedFood.getName(),
                savedFood.getPrice(),
                savedFood.getStall().getId(),
                savedFood.getPhotoFilePath()
        );
    }

    public List<FoodResponseDto> getFoodsByStall(Long id) {
        List<Food> foods = foodRepository.findByStallId(id);

        if (foods.isEmpty()) {
            throw new FoodNotFoundException("No food data for this stall");
        }

        return foods.stream()
                .map(food -> new FoodResponseDto(
                        food.getId(),
                        food.getName(),
                        food.getPrice(),
                        food.getStall().getId(),
                        food.getPhotoFilePath()
                ))
                .toList();
    }

    public FoodResponseDto getFoodById(Long id) {
        return foodRepository.findById(id)
                .map(food -> new FoodResponseDto(
                        food.getId(),
                        food.getName(),
                        food.getPrice(),
                        food.getStall().getId(),
                        food.getPhotoFilePath()
                ))
                .orElseThrow(() -> new FoodNotFoundException("No food found with id " + id));
    }

}
