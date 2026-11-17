package com.flowershop.flowershop_web.model;


public class Staff {
    private String id;
    private String fullName;
    private String username;
    private String role;

    public Staff(String id, String fullName, String username, String role) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.role = role;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public boolean isManager() { return "MANAGER".equalsIgnoreCase(role); }
}