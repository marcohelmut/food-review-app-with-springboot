package com.marcohelmut.foodreviewapp.repositories;

import com.marcohelmut.foodreviewapp.entities.Stall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StallRepository extends JpaRepository<Stall, Long> {
}
