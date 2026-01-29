package com.example.productreview.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ReviewRequestDTO {
	@Min(1)
    @Max(5)
    private int rating;

    @NotBlank
    private String reviewText;

    public ReviewRequestDTO() {
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

}
