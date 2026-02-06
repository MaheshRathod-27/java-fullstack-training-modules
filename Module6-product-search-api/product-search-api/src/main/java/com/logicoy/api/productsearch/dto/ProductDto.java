package com.logicoy.api.productsearch.dto;

import io.swagger.v3.oas.annotations.media.Schema;

//This is a response DTO and part of the public API contract.

@Schema(description = "Product information returned in search results")
public class ProductDto {

   
    @Schema(example = "P1001")
    private String productId;

   
    private String name;

   
    private String category;

   
    private double price;

   
    private double rating;

    private boolean inStock;

    public String getProductId() {
        return productId;
    }

 
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
