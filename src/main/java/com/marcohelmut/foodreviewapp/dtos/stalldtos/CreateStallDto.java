package com.marcohelmut.foodreviewapp.dtos.stalldtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateStallDto (
        @NotBlank(message = "Stall name cannot be empty")
        @Size(max = 50, message = "Stall name must have less than 50 characters")
        String name,

        @NotBlank(message = "Please include a picture of the stall")
        @Size(max = 100, message = "Stall photo file path must be below 100 characters")
        String photoFilePath
) {}
