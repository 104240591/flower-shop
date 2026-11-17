package com.flowershop.flowershop_web.model;

public abstract class Item {
    protected String id;
    protected String name;
    protected double basePrice;

    public Item(String id, String name, double basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public abstract double getCalculatedPrice();
    public abstract boolean isAvailable();

    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", getCalculatedPrice()) + ")";
    }
}