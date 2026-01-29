package com.example.productreview.controller;

import com.example.productreview.dto.ReviewRequestDTO;
import com.example.productreview.dto.ReviewResponseDTO;
import com.example.productreview.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping
    public ResponseEntity<String> addReview(@PathVariable Long productId,@Valid @RequestBody ReviewRequestDTO dto) {
        reviewService.addReview(productId, dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Review added successfully");
    }


    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getReviews(
            @PathVariable Long productId) {

        return ResponseEntity.ok(reviewService.getReviews(productId));
    }
}
