package com.flowershop.flowershop_web.model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Flower extends Item {
    private String color;
    private int vaseLifeDays;
    private LocalDate harvestedDate;
    private int stockQty;
    private String origin;

    public Flower(String id, String name, double basePrice, String color, int vaseLifeDays, LocalDate harvestedDate, int stockQty, String origin) {
        super(id, name, basePrice);
        this.color = color;
        this.vaseLifeDays = vaseLifeDays;
        this.harvestedDate = harvestedDate;
        this.stockQty = stockQty;
        this.origin = origin;
    }

    public boolean isFresh() {
        long daysPassed = ChronoUnit.DAYS.between(harvestedDate, LocalDate.now());
        return daysPassed <= vaseLifeDays;
    }

    @Override
    public double getCalculatedPrice() {
        // 40% clearance discount for flowers past their vase life
        return isFresh() ? this.basePrice : this.basePrice * 0.60;
    }

    @Override
    public boolean isAvailable() {
        return this.stockQty > 0;
    }

    public int getStockQty() { return stockQty; }
    public void reduceStock(int qty) { this.stockQty = Math.max(0, this.stockQty - qty); }
    public String getColor() { return color; }
    public int getVaseLifeDays() { return vaseLifeDays; }
    public LocalDate getHarvestedDate() { return harvestedDate; }
    public String getOrigin() { return origin; }
}