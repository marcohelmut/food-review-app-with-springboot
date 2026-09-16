package com.marcohelmut.foodreviewapp.dtos.fooddtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateFoodDto (
        @NotBlank(message = "Food name cannot be empty")
        @Size(max = 50, message = "Food name must have less than 50 characters")
        String name,

        @NotNull(message = "Food must have a price")
        Double price,

        @NotNull(message = "Food should have a stall associated with it")
        Long stallId,

        @NotBlank(message = "Please include a picture of the food")
        @Size(max = 100, message = "Stall photo file path must be below 100 characters")
        String foodPhotoFilePath
) {}
