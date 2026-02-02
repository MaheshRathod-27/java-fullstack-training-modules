package com.logicoy.inventory.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.logicoy.inventory.exception.ProductNotFoundException;
import com.logicoy.inventory.exception.InsufficientStockException;

@Service
public class InventoryService {

    private static final Logger log =
            LoggerFactory.getLogger(InventoryService.class);

    // In-memory product stock (simulating DB)
    private static final Map<Long, Integer> productStock = new HashMap<>();

    static {
        productStock.put(1L, 10);
        productStock.put(2L, 5);
        productStock.put(3L, 8);
        productStock.put(4L, 0);
        productStock.put(5L, 12);
    }

    public void reduceStock(Long productId, int quantity) {

        // Check if product exists
        if (!productStock.containsKey(productId)) {
            throw new ProductNotFoundException(productId);
        }

        int currentStock = productStock.get(productId);

        log.debug("Fetched current stock for product {}: {}", productId, currentStock);
        log.debug("Requested quantity for product {}: {}", productId, quantity);

        // Stock unavailable
        if (currentStock <= 0) {
            throw new InsufficientStockException(productId, quantity, currentStock);
        }

        // Requested quantity exceeds available stock
        if (quantity > currentStock) {
            throw new InsufficientStockException(productId, quantity, currentStock);
        }

        int updatedStock = currentStock - quantity;

        log.debug("Calculated updated stock for product {}: {}", productId, updatedStock);

        // Simulate DB update
        productStock.put(productId, updatedStock);

        log.info(
            "Stock updated successfully for product {}. Previous stock: {}, Requested quantity: {}, Remaining stock: {}",
            productId, currentStock, quantity, updatedStock
        );
    }
}
