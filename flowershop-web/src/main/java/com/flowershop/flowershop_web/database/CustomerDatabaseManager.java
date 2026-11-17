package com.flowershop.flowershop_web.database;
import com.flowershop.flowershop_web.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDatabaseManager {

    public Customer findById(String customerId) {
        String sql = "SELECT customer_id, full_name, phone, membership_tier, total_spent FROM CUSTOMER WHERE customer_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getString("customer_id"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("membership_tier"),
                        rs.getDouble("total_spent")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, full_name, phone, membership_tier, total_spent FROM CUSTOMER ORDER BY customer_id";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(new Customer(
                    rs.getString("customer_id"),
                    rs.getString("full_name"),
                    rs.getString("phone"),
                    rs.getString("membership_tier"),
                    rs.getDouble("total_spent")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public boolean insertCustomer(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (customer_id, full_name, phone, membership_tier, total_spent) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getId());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getMembershipTier());
            stmt.setDouble(5, customer.getTotalSpent());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateTotalSpent(String customerId, double additionalAmount) {
        String sql = "UPDATE CUSTOMER SET total_spent = total_spent + ? WHERE customer_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, additionalAmount);
            stmt.setString(2, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}