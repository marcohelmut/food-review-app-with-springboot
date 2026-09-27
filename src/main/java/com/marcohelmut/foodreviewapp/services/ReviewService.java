package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.dtos.reviewdtos.CreateReviewDto;
import com.marcohelmut.foodreviewapp.dtos.reviewdtos.ReviewResponseDto;
import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.entities.Review;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import com.marcohelmut.foodreviewapp.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, FoodRepository foodRepository) {
        this.reviewRepository = reviewRepository;
        this.foodRepository = foodRepository;
    }

    public ReviewResponseDto saveReview(CreateReviewDto dto) {
        Food food = foodRepository.getReferenceById(dto.foodId());

        Review review = new Review();
        review.setStudentNumber(dto.studentNumber());
        review.setPriceScore(dto.priceScore());
        review.setTasteScore(dto.tasteScore());
        review.setCleanlinessScore(dto.cleanlinessScore());
        review.setComment(dto.comment());
        review.setFood(food);
        review.setCreatedAt(Instant.now());

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(
                savedReview.getId(),
                savedReview.getStudentNumber(),
                savedReview.getPriceScore(),
                savedReview.getTasteScore(),
                savedReview.getCleanlinessScore(),
                savedReview.getComment(),
                savedReview.getFood().getId()
        );
    }

}
