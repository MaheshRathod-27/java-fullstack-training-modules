package com.example.productreview.dto;

public class ReviewResponseDTO {
	private final int rating;
    private final String reviewText;

    public ReviewResponseDTO(int rating, String reviewText) {
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

}
