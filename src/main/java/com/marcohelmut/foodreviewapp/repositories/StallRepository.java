package com.marcohelmut.foodreviewapp.repositories;

import com.marcohelmut.foodreviewapp.dtos.stalldtos.StallResponseDto;
import com.marcohelmut.foodreviewapp.entities.Stall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StallRepository extends JpaRepository<Stall, Long> {
    Optional<Stall> findByNameIgnoreCase(String name);
}
