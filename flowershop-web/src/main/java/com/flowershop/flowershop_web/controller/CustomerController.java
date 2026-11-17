package com.flowershop.flowershop_web.controller;


import com.flowershop.flowershop_web.database.CustomerDatabaseManager;
import com.flowershop.flowershop_web.model.Customer;
import java.util.List;

public class CustomerController {
    private final CustomerDatabaseManager customerDbManager;

    public CustomerController() {
        this.customerDbManager = new CustomerDatabaseManager();
    }

    public Customer getCustomerById(String id) {
        return customerDbManager.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDbManager.getAllCustomers();
    }

    public boolean registerCustomer(String id, String fullName, String phone, String tier) {
        Customer newCustomer = new Customer(id, fullName, phone, tier, 0.0);
        return customerDbManager.insertCustomer(newCustomer);
    }
}