package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.dtos.stalldtos.CreateStallDto;
import com.marcohelmut.foodreviewapp.dtos.stalldtos.StallResponseDto;
import com.marcohelmut.foodreviewapp.services.StallService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stalls")
public class StallController {

    private static final Logger logger = LoggerFactory.getLogger(StallController.class);

    private final StallService stallService;

    @Autowired
    public StallController(StallService stallService) {
        this.stallService = stallService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<StallResponseDto> createStall(@Valid @RequestBody CreateStallDto dto) {
        logger.info("Create stall request received: {}", dto.name());
        StallResponseDto savedStall = stallService.saveStall(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStall);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<StallResponseDto> getStallByName(@PathVariable String name) {
        StallResponseDto stall = stallService.getStallByName(name);
        return ResponseEntity.ok(stall);
    }

    @GetMapping
    public ResponseEntity<List<StallResponseDto>> getStalls() {
        List<StallResponseDto> stalls = stallService.getStalls();
        return ResponseEntity.ok(stalls);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteStallById(@PathVariable Long id) {
        logger.info("Delete stall request received: {}", id);
        stallService.deleteStall(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StallResponseDto> getStall(@PathVariable Long id) {
        return ResponseEntity.ok(stallService.getStall(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getStallCount() {
        return ResponseEntity.ok(stallService.getStallCount());
    }

}
