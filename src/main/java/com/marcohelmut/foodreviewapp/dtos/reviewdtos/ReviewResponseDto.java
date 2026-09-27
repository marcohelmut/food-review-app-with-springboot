package com.marcohelmut.foodreviewapp.dtos.reviewdtos;

public record ReviewResponseDto(
        Long id,
        Integer studentNumber,
        Integer priceScore,
        Integer tasteScore,
        Integer cleanlinessScore,
        String comment,
        Long foodId
) {
}
