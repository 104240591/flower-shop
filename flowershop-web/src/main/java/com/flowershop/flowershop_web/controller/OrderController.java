package com.flowershop.flowershop_web.controller;


import com.flowershop.flowershop_web.database.*;
import com.flowershop.flowershop_web.model.*;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private final OrderDatabaseManager orderDbManager;
    private final FlowerDatabaseManager flowerDbManager;
    private final CustomerDatabaseManager customerDbManager;

    public OrderController() {
        this.orderDbManager = new OrderDatabaseManager();
        this.flowerDbManager = new FlowerDatabaseManager();
        this.customerDbManager = new CustomerDatabaseManager();
    }

    public List<Flower> loadAvailableInventory() {
        return flowerDbManager.getAllFlowers();
    }

    public boolean submitOrder(Customer customer, Staff staff, List<OrderItem> items) {
        if (items == null || items.isEmpty()) return false;

        String generatedOrderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Order order = new Order(generatedOrderId, customer, staff);
        for (OrderItem it : items) {
            order.addItem(it.getItem(), it.getQuantity());
        }

        // 1. Deduct inventory in PostgreSQL
        try {
            for (OrderItem it : items) {
                flowerDbManager.updateStock(it.getItem().getId(), it.getQuantity());
            }
        } catch (SQLException e) {
            System.err.println("Inventory deduction failed: " + e.getMessage());
            return false;
        }

        // 2. Persist Order and update customer spend
        boolean saved = orderDbManager.insertOrder(order);
        if (saved && customer != null) {
            customerDbManager.updateTotalSpent(customer.getId(), order.calculateTotal());
        }
        return saved;
    }
}