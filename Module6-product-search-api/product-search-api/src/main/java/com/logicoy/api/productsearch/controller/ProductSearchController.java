package com.logicoy.api.productsearch.controller;

import com.logicoy.api.productsearch.dto.*;
import com.logicoy.api.productsearch.exception.InvalidFilterException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

// REST controller for product search,Contract-focused implementation using mock data (no DB).
@RestController
@RequestMapping("/api/v1/products")
@Tag(
    name = "Product Search API",
    description = "Search products using filters, sorting and pagination"
)
public class ProductSearchController {

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> searchProducts(

        @Parameter(description = "Search keyword", example = "iphone")
        @RequestParam(required = false) String keyword,

        @Parameter(description = "Minimum price", example = "1000")
        @RequestParam(required = false) Double minPrice,

        @Parameter(description = "Maximum price", example = "50000")
        @RequestParam(required = false) Double maxPrice,

        @Parameter(description = "Sort by: priceAsc, priceDesc, rating",example = "priceAsc")
        @RequestParam(required = false) String sortBy,

        @Parameter(description = "Page number (starts from 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,

        @Parameter(description = "Page size", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {

        // Business validation: invalid price range
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            throw new InvalidFilterException("minPrice cannot be greater than maxPrice");
        }

        //Business validation: supported sort values
        if (sortBy != null &&!(sortBy.equals("priceAsc")|| sortBy.equals("priceDesc")|| sortBy.equals("rating"))) {
            throw new InvalidFilterException("Unsupported sort value");
        }

        // Pagination metadata
        PaginationMeta meta = new PaginationMeta();
        meta.setPage(page);
        meta.setSize(size);

        SearchResponse response = new SearchResponse();

   
        // MOCK DATA (acts like DB records)
        
        ProductDto product = new ProductDto();
        product.setProductId("P1001");
        product.setName("iPhone 15");
        product.setCategory("electronics");
        product.setPrice(79999);
        product.setRating(4.6);
        product.setInStock(true);

        List<ProductDto> products = List.of(product);
        
        // APPLY SORTING for demo
      
        if ("priceAsc".equals(sortBy)) {
            products = products.stream()
                    .sorted(Comparator.comparing(ProductDto::getPrice))
                    .toList();

        } else if ("priceDesc".equals(sortBy)) {
            products = products.stream()
                    .sorted(Comparator.comparing(ProductDto::getPrice).reversed())
                    .toList();

        } else if ("rating".equals(sortBy)) {
            products = products.stream()
                    .sorted(Comparator.comparing(ProductDto::getRating).reversed())
                    .toList();
        }

        response.setData(products);
        meta.setTotalElements(products.size());
        meta.setTotalPages(1);

        response.setMeta(meta);
        return ResponseEntity.ok(response);
    }
}
