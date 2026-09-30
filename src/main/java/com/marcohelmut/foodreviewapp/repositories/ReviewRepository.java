package com.marcohelmut.foodreviewapp.repositories;

import com.marcohelmut.foodreviewapp.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFoodId(Long id);
}
