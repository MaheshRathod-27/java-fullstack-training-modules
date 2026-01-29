package com.example.productreview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    private String reviewText;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    protected Review() {
        // required by JPA
    }

    public Review(int rating, String reviewText, Product product) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }
}
