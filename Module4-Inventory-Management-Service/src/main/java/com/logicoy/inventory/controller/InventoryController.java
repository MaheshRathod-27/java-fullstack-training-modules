package com.logicoy.inventory.controller;

import org.springframework.web.bind.annotation.*;

import com.logicoy.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")// Base URL for all inventory-related endpoints
public class InventoryController {

	 // Service layer dependency to handle business logic
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    
    // API to reduce product stock based on product ID and quantity
    @PostMapping("/{productId}/reduce")
    public String reduceStock(@PathVariable Long productId,@RequestParam int quantity) 
    {
        inventoryService.reduceStock(productId, quantity);
        return "Stock updated successfully";
    }
}
