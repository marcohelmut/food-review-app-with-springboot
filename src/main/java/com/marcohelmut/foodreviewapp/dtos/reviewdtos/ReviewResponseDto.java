package com.marcohelmut.foodreviewapp.dtos.reviewdtos;

import java.time.Instant;

public record ReviewResponseDto(
        Long id,
        Integer studentNumber,
        String studentNickname,
        Integer priceScore,
        Integer tasteScore,
        Integer cleanlinessScore,
        String comment,
        Long foodId,
        Instant createdAt
) {
}
