package com.logicoy.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.logicoy.inventory.exception.ProductNotFoundException;
import com.logicoy.inventory.exception.InsufficientStockException;

@Service
public class InventoryService {

    private static final Logger log =
            LoggerFactory.getLogger(InventoryService.class);

    public void reduceStock(Long productId, int quantity) {

        // Temporary stock value to simulate database data
        int currentStock = 10;

        log.debug("Fetched current stock for product {}: {}", productId, currentStock);
        log.debug("Requested quantity for product {}: {}", productId, quantity);

        // Product does not exist or stock is unavailable
        if (currentStock <= 0) {
            throw new ProductNotFoundException(productId);
        }

        // Requested quantity exceeds available stock
        if (quantity > currentStock) {
            throw new InsufficientStockException(productId, quantity, currentStock);
        }

        int updatedStock = currentStock - quantity;

        log.debug("Calculated updated stock for product {}: {}", productId, updatedStock);

        // Placeholder for database update logic
        log.info("Stock updated successfully for product {}. New stock: {}",
                productId, updatedStock);
    }
}
