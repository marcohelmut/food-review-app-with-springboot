package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.dtos.stalldtos.CreateStallDto;
import com.marcohelmut.foodreviewapp.dtos.stalldtos.StallResponseDto;
import com.marcohelmut.foodreviewapp.entities.Stall;
import com.marcohelmut.foodreviewapp.exceptions.stallexceptions.StallNotFoundException;
import com.marcohelmut.foodreviewapp.repositories.StallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StallService {

    private final StallRepository stallRepository;

    @Autowired
    public StallService(StallRepository stallRepository) {
        this.stallRepository = stallRepository;
    }

    public StallResponseDto saveStall(CreateStallDto dto) {
        Stall stall = new Stall();
        stall.setName(dto.name().trim());
        stall.setPhotoFilePath(dto.photoFilePath());

        Stall savedStall = stallRepository.save(stall);

        return new StallResponseDto(
                savedStall.getId(),
                savedStall.getName(),
                savedStall.getPhotoFilePath()
        );
    }

    public StallResponseDto getStallByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Stall name must not be blank");
        }

        return stallRepository.findByNameIgnoreCase(name.trim())
                .map(stall -> new StallResponseDto(
                        stall.getId(),
                        stall.getName(),
                        stall.getPhotoFilePath()
                ))
                .orElseThrow(() -> new StallNotFoundException(name));
    }

}
