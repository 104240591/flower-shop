package com.flowershop.flowershop_web.database;
import com.flowershop.flowershop_web.model.Order;
import com.flowershop.flowershop_web.model.OrderItem;
import java.sql.*;

public class OrderDatabaseManager {

    public boolean insertOrder(Order order) {
        String insertOrderSql = "INSERT INTO ORDERS (order_id, customer_id, staff_id, total_amount, order_status) VALUES (?, ?, ?, ?, ?)";
        String insertItemSql = "INSERT INTO ORDER_ITEM (order_item_id, order_id, item_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection()) {
            conn.setAutoCommit(false); // Begin transaction

            try (PreparedStatement orderStmt = conn.prepareStatement(insertOrderSql);
                 PreparedStatement itemStmt = conn.prepareStatement(insertItemSql)) {

                orderStmt.setString(1, order.getOrderId());
                orderStmt.setString(2, order.getCustomer() != null ? order.getCustomer().getId() : null);
                orderStmt.setString(3, order.getHandledBy() != null ? order.getHandledBy().getId() : null);
                orderStmt.setDouble(4, order.calculateTotal());
                orderStmt.setString(5, order.getStatus());
                orderStmt.executeUpdate();

                int idx = 1;
                for (OrderItem line : order.getItems()) {
                    itemStmt.setString(1, order.getOrderId() + "-L" + (idx++));
                    itemStmt.setString(2, order.getOrderId());
                    itemStmt.setString(3, line.getItem().getId());
                    itemStmt.setInt(4, line.getQuantity());
                    itemStmt.setDouble(5, line.getItem().getCalculatedPrice());
                    itemStmt.setDouble(6, line.getSubtotal());
                    itemStmt.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}