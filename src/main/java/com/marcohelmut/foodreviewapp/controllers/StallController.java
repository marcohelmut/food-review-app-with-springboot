package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.dtos.stalldtos.CreateStallDto;
import com.marcohelmut.foodreviewapp.dtos.stalldtos.StallResponseDto;
import com.marcohelmut.foodreviewapp.services.StallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stalls")
public class StallController {

    private final StallService stallService;

    @Autowired
    public StallController(StallService stallService) {
        this.stallService = stallService;
    }

    @PostMapping
    public ResponseEntity<StallResponseDto> createStall(@Valid @RequestBody CreateStallDto dto) {
        StallResponseDto savedStall = stallService.saveStall(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStall);
    }

    @GetMapping("/{name}")
    public ResponseEntity<StallResponseDto> getStallByName(@PathVariable String name) {
        StallResponseDto stall = stallService.getStallByName(name);
        return ResponseEntity.ok(stall);
    }

}
