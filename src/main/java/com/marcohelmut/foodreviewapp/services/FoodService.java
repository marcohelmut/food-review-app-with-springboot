package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.dtos.fooddtos.CreateFoodDto;
import com.marcohelmut.foodreviewapp.dtos.fooddtos.FoodResponseDto;
import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.entities.Review;
import com.marcohelmut.foodreviewapp.entities.Stall;
import com.marcohelmut.foodreviewapp.exceptions.foodexceptions.FoodNotFoundException;
import com.marcohelmut.foodreviewapp.exceptions.stallexceptions.StallNotFoundException;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import com.marcohelmut.foodreviewapp.repositories.ReviewRepository;
import com.marcohelmut.foodreviewapp.repositories.StallRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final StallRepository stallRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, StallRepository stallRepository, ReviewRepository reviewRepository) {
        this.foodRepository = foodRepository;
        this.stallRepository = stallRepository;
        this.reviewRepository = reviewRepository;
    }

    public FoodResponseDto saveFood(CreateFoodDto dto) {
        Stall stall = stallRepository.findById(dto.stallId())
                .orElseThrow(() -> new StallNotFoundException("Stall with id " + dto.stallId() + " does not exist"));

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
        if (!stallRepository.existsById(id)) {
            throw new StallNotFoundException("Stall with id " + id + " does not exist");
        }

        return foodRepository.findByStallId(id).stream()
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

    @Transactional
    public void deleteFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException("Food with id " + id + " does not exist"));
        foodRepository.delete(food);
    }

    public long getTotalFoodCount() {
        return foodRepository.count();
    }

    public List<FoodResponseDto> getPriceRanking() {
        return reviewRepository.findFoodsRankedByAveragePriceScore().stream()
                .map(food -> new FoodResponseDto(
                        food.getId(),
                        food.getName(),
                        food.getPrice(),
                        food.getStall().getId(),
                        food.getPhotoFilePath()
                )) // or food -> new FoodResponseDto(food)
                .toList();
    }

    public List<FoodResponseDto> getTasteRanking() {
        return reviewRepository.findFoodsRankedByAverageTasteScore().stream()
                .map(food -> new FoodResponseDto(
                        food.getId(),
                        food.getName(),
                        food.getPrice(),
                        food.getStall().getId(),
                        food.getPhotoFilePath()
                ))
                .toList();
    }

    public List<FoodResponseDto> getCleanlinessRanking() {
        return reviewRepository.findFoodsRankedByAverageCleanlinessScore().stream()
                .map(food -> new FoodResponseDto(
                        food.getId(),
                        food.getName(),
                        food.getPrice(),
                        food.getStall().getId(),
                        food.getPhotoFilePath()
                ))
                .toList();
    }

}
