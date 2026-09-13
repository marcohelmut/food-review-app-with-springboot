package com.marcohelmut.foodreviewapp.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateStallDto (
        @NotBlank(message = "Stall name cannot be empty")
        String name,

        String photoFilePath
) {}
