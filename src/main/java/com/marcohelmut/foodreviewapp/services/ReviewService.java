package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.dtos.reviewdtos.CreateReviewDto;
import com.marcohelmut.foodreviewapp.dtos.reviewdtos.ReviewResponseDto;
import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.entities.Review;
import com.marcohelmut.foodreviewapp.exceptions.foodexceptions.FoodNotFoundException;
import com.marcohelmut.foodreviewapp.repositories.FoodRepository;
import com.marcohelmut.foodreviewapp.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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
        Food food = foodRepository.findById(dto.foodId())
                .orElseThrow(() -> new FoodNotFoundException("Food with id " + dto.foodId() + " does not exist"));

        Review review = new Review();
        review.setStudentNumber(dto.studentNumber());
        review.setStudentNickname(dto.studentNickname());
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
                savedReview.getStudentNickname(),
                savedReview.getPriceScore(),
                savedReview.getTasteScore(),
                savedReview.getCleanlinessScore(),
                savedReview.getComment(),
                savedReview.getFood().getId(),
                savedReview.getCreatedAt()
        );
    }

    public List<ReviewResponseDto> getReviewsByFood(Long id) {
        if (!foodRepository.existsById(id)) {
            throw new FoodNotFoundException("Food with id " + id + " not found");
        }

        return reviewRepository.findByFoodId(id).stream()
                .map(review -> new ReviewResponseDto(
                        review.getId(),
                        review.getStudentNumber(),
                        review.getStudentNickname(),
                        review.getPriceScore(),
                        review.getTasteScore(),
                        review.getCleanlinessScore(),
                        review.getComment(),
                        review.getFood().getId(),
                        review.getCreatedAt()
                ))
                .toList();
    }

    public Long getReviewCount() {
        return reviewRepository.count();
    }

}
