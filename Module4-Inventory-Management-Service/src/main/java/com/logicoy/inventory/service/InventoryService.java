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

        // Simulated database stock value
        int currentStock = 10;

        log.debug("Fetched current stock for product {}: {}", productId, currentStock);
        log.debug("Requested quantity for product {}: {}", productId, quantity);

        if (currentStock <= 0) {
            throw new ProductNotFoundException(productId);
        }

        if (quantity > currentStock) {
            throw new InsufficientStockException(productId, quantity, currentStock);
        }

        int updatedStock = currentStock - quantity;

        log.debug("Calculated updated stock for product {}: {}", productId, updatedStock);

        // Simulate DB update here

        log.info("Stock updated successfully for product {}. New stock: {}",
                productId, updatedStock);
    }
}
