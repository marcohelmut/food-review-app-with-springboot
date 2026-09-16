package com.marcohelmut.foodreviewapp.dtos.fooddtos;

public record FoodResponseDto (
        Long id,
        String name,
        Double price,
        Long stallId,
        String foodPhotoFilePath
) {}
