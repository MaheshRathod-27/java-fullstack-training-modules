package com.example.productreview.service;


import com.example.productreview.dto.ReviewRequestDTO;
import com.example.productreview.dto.ReviewResponseDTO;
import com.example.productreview.entity.Product;
import com.example.productreview.entity.Review;
import com.example.productreview.exception.ProductNotFoundException;
import com.example.productreview.repository.ProductRepository;
import com.example.productreview.repository.ReviewRepository;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(ProductRepository productRepository,
                         ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public void addReview(Long productId, ReviewRequestDTO dto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Review review = new Review(
                dto.getRating(),
                dto.getReviewText(),
                product
        );

        reviewRepository.save(review);
    }

    public List<ReviewResponseDTO> getReviews(Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }

        return reviewRepository.findByProductId(productId)
                .stream()
                .map(r -> new ReviewResponseDTO(
                        r.getRating(),
                        r.getReviewText()))
                .collect(Collectors.toList());
    }
}
