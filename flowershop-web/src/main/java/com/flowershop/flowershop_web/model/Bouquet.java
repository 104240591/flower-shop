package com.flowershop.flowershop_web.model;

import java.util.ArrayList;
import java.util.List;

public class Bouquet extends Item {
    private List<Flower> flowers = new ArrayList<>();
    private Accessory wrapping;
    private double laborFee;

    public Bouquet(String id, String name, double laborFee) {
        super(id, name, 0.0);
        this.laborFee = laborFee;
    }

    // Method Overloading 1: Single stem
    public void addFlower(Flower flower) {
        if (flower != null && flower.isAvailable()) {
            this.flowers.add(flower);
        }
    }

    // Method Overloading 2: Multiple stems
    public void addFlower(Flower flower, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addFlower(flower);
        }
    }

    public void setWrapping(Accessory wrapping) {
        this.wrapping = wrapping;
    }

    @Override
    public double getCalculatedPrice() {
        double total = this.laborFee;
        for (Flower f : flowers) {
            total += f.getCalculatedPrice();
        }
        if (wrapping != null) {
            total += wrapping.getCalculatedPrice();
        }
        return total;
    }

    @Override
    public boolean isAvailable() {
        return !flowers.isEmpty();
    }

    public List<Flower> getFlowers() { return flowers; }
    public Accessory getWrapping() { return wrapping; }
    public double getLaborFee() { return laborFee; }
}