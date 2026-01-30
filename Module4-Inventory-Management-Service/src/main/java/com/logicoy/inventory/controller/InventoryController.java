package com.logicoy.inventory.controller;

import org.springframework.web.bind.annotation.*;

import com.logicoy.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{productId}/reduce")
    public String reduceStock(
            @PathVariable Long productId,
            @RequestParam int quantity) {

        inventoryService.reduceStock(productId, quantity);
        return "Stock updated successfully";
    }
}
