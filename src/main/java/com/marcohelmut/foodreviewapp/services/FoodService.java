package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food saveFood(Food food) {
        food.setName(food.getName().trim());
        return foodRepository.save(food);
    }

}
