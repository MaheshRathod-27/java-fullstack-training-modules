package com.example.productreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productreview.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
