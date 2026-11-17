package com.flowershop.flowershop_web.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private Staff handledBy;
    private List<OrderItem> items = new ArrayList<>();
    private LocalDateTime orderDate;
    private String status;

    public Order(String orderId, Customer customer, Staff handledBy) {
        this.orderId = orderId;
        this.customer = customer;
        this.handledBy = handledBy;
        this.orderDate = LocalDateTime.now();
        this.status = "PAID";
    }

    public void addItem(Item item, int qty) {
        items.add(new OrderItem(item, qty));
    }

    public double calculateTotal() {
        double subtotal = 0;
        for (OrderItem item : items) {
            subtotal += item.getSubtotal();
        }
        return customer != null ? customer.applyDiscount(subtotal) : subtotal;
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Staff getHandledBy() { return handledBy; }
    public List<OrderItem> getItems() { return items; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
}