package com.flowershop.flowershop_web.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/flowershop_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password"; // Update with your PostgreSQL password

    private DBConnection() {}

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}