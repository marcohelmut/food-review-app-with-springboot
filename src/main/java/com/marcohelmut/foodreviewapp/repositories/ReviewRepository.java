package com.marcohelmut.foodreviewapp.repositories;

import com.marcohelmut.foodreviewapp.entities.Food;
import com.marcohelmut.foodreviewapp.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFoodId(Long id);

    @Query("SELECT r.food FROM Review r GROUP BY r.food.id ORDER BY AVG(r.priceScore) DESC")
    List<Food> findFoodsRankedByAveragePriceScore();

    @Query("SELECT r.food FROM Review r GROUP BY r.food.id ORDER BY AVG(r.tasteScore) DESC")
    List<Food> findFoodsRankedByAverageTasteScore();

    @Query("SELECT r.food FROM Review r GROUP BY r.food.id ORDER BY AVG(r.cleanlinessScore) DESC")
    List<Food> findFoodsRankedByAverageCleanlinessScore();
}
