package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.entities.Stall;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import com.marcohelmut.foodreviewapp.repositories.ReviewRepository;
import com.marcohelmut.foodreviewapp.repositories.StallRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    private final StallRepository stallRepository;
    private final FoodRepository foodRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public AppService(StallRepository stallRepository, FoodRepository foodRepository, ReviewRepository reviewRepository) {
        this.stallRepository = stallRepository;
        this.foodRepository = foodRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Stall saveStall(Stall stall) {
        if (stall.getName() == null || stall.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Stall name cannot be empty");
        }

        stall.setName(stall.getName().trim());
        return stallRepository.save(stall);
    }
}
