package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.dtos.fooddtos.CreateFoodDto;
import com.marcohelmut.foodreviewapp.dtos.fooddtos.FoodResponseDto;
import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.entities.Stall;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import com.marcohelmut.foodreviewapp.repositories.StallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Food savedFood = foodRepository.save(food);

        return new FoodResponseDto(
                savedFood.getId(),
                savedFood.getName(),
                savedFood.getPrice(),
                savedFood.getStall().getId(),
                savedFood.getPhotoFilePath()
        );
    }

}
