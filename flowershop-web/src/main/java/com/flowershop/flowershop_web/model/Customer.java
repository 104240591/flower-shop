package com.flowershop.flowershop_web.model;


public class Customer {
    private String id;
    private String fullName;
    private String phone;
    private String membershipTier;
    private double totalSpent;

    public Customer(String id, String fullName, String phone, String membershipTier, double totalSpent) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.membershipTier = membershipTier;
        this.totalSpent = totalSpent;
    }

    public double applyDiscount(double subtotal) {
        if ("GOLD".equalsIgnoreCase(membershipTier)) return subtotal * 0.85;
        if ("SILVER".equalsIgnoreCase(membershipTier)) return subtotal * 0.95;
        return subtotal;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getMembershipTier() { return membershipTier; }
    public double getTotalSpent() { return totalSpent; }
}