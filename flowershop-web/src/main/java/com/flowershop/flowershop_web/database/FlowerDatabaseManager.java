package com.flowershop.flowershop_web.database;


import com.flowershop.flowershop_web.model.Flower;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerDatabaseManager {

    public List<Flower> getAllFlowers() {
        List<Flower> flowers = new ArrayList<>();
        String sql = "SELECT i.item_id, i.item_name, i.base_price, f.color, f.vase_life_days, f.harvested_date, f.stock_qty, f.origin " +
                     "FROM ITEM i JOIN FLOWER f ON i.item_id = f.flower_id ORDER BY i.item_id";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Flower flower = new Flower(
                    rs.getString("item_id"),
                    rs.getString("item_name"),
                    rs.getDouble("base_price"),
                    rs.getString("color"),
                    rs.getInt("vase_life_days"),
                    rs.getDate("harvested_date").toLocalDate(),
                    rs.getInt("stock_qty"),
                    rs.getString("origin")
                );
                flowers.add(flower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flowers;
    }

    public void updateStock(String flowerId, int deductedQty) throws SQLException {
        String sql = "UPDATE FLOWER SET stock_qty = stock_qty - ? WHERE flower_id = ? AND stock_qty >= ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deductedQty);
            stmt.setString(2, flowerId);
            stmt.setInt(3, deductedQty);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Insufficient stock or flower ID not found: " + flowerId);
            }
        }
    }
}