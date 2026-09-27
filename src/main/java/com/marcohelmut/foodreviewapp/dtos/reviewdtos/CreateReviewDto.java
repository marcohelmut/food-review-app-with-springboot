package com.marcohelmut.foodreviewapp.dtos.reviewdtos;

import jakarta.validation.constraints.*;

public record CreateReviewDto(
        @NotNull(message = "Student Number cannot be empty.")
        @Min(value = 100000, message = "Student number must be six digits.")
        @Max(value = 999999, message = "Student number must be six digits.")
        Integer studentNumber,

        @NotNull(message = "Please include a price score.")
        @Min(value = 1, message = "Price score must be between 1 to 5.")
        @Max(value = 5, message = "Price score must be between 1 to 5.")
        Integer priceScore,

        @NotNull(message = "Please include a taste score.")
        @Min(value = 1, message = "Taste score must be between 1 to 5.")
        @Max(value = 5, message = "Taste score must be between 1 to 5.")
        Integer tasteScore,

        @NotNull(message = "Please include a cleanliness score.")
        @Min(value = 1, message = "Cleanliness score must be between 1 to 5.")
        @Max(value = 5, message = "Cleanliness score must be between 1 to 5.")
        Integer cleanlinessScore,

        String comment,

        @NotNull(message = "A review must be associated with a food item.")
        Long foodId
) {
}
