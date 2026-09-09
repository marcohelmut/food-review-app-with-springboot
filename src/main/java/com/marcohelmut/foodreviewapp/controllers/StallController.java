package com.marcohelmut.foodreviewapp.controllers;

import com.marcohelmut.foodreviewapp.entities.Stall;
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
    public ResponseEntity<Stall> createStall(@Valid @RequestBody Stall stall) {
        Stall savedStall = stallService.saveStall(stall);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStall);
    }

    @GetMapping("/stalls/{name}")
    public ResponseEntity<Stall> getStallByName(@PathVariable String name) {
        Stall stall = stallService.getStallByName(name);
        return ResponseEntity.ok(stall);
    }

}
