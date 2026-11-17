package com.flowershop.flowershop_web.controller;


import com.flowershop.flowershop_web.database.FlowerDatabaseManager;
import com.flowershop.flowershop_web.model.Flower;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryController {
    private final FlowerDatabaseManager flowerDbManager;

    public InventoryController() {
        this.flowerDbManager = new FlowerDatabaseManager();
    }

    public List<Flower> getAllFlowers() {
        return flowerDbManager.getAllFlowers();
    }

    public List<Flower> getLowStockFlowers(int threshold) {
        return flowerDbManager.getAllFlowers().stream()
                .filter(f -> f.getStockQty() <= threshold)
                .collect(Collectors.toList());
    }

    public List<Flower> getExpiredFlowers() {
        return flowerDbManager.getAllFlowers().stream()
                .filter(f -> !f.isFresh())
                .collect(Collectors.toList());
    }
}