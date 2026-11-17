package com.flowershop.flowershop_web.model;


public class Accessory extends Item {
    private String material;
    private String accessoryType;
    private int stockQty;

    public Accessory(String id, String name, double basePrice, String material, String accessoryType, int stockQty) {
        super(id, name, basePrice);
        this.material = material;
        this.accessoryType = accessoryType;
        this.stockQty = stockQty;
    }

    @Override
    public double getCalculatedPrice() {
        return this.basePrice;
    }

    @Override
    public boolean isAvailable() {
        return this.stockQty > 0;
    }

    public String getMaterial() { return material; }
    public String getAccessoryType() { return accessoryType; }
    public int getStockQty() { return stockQty; }
    public void reduceStock(int qty) { this.stockQty = Math.max(0, this.stockQty - qty); }
}