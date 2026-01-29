package com.example.productreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productreview.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	 List<Review> findByProductId(Long productId);

}
