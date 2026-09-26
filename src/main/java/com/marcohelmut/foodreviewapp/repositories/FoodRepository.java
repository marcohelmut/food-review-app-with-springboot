package com.marcohelmut.foodreviewapp.repositories;

import com.marcohelmut.foodreviewapp.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByStallId(Long id);
}
