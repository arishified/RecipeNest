package com.recipenest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "MySQL JDBC driver is missing from the application.", e);
        }

        String host = System.getenv("MYSQLHOST");
        String url;
        String username;
        String password;

        if (host != null && !host.isBlank()) {
            String port = System.getenv("MYSQLPORT");
            String database = System.getenv("MYSQLDATABASE");
            username = System.getenv("MYSQLUSER");
            password = System.getenv("MYSQLPASSWORD");

            if (port == null || database == null
                    || username == null || password == null) {
                throw new SQLException(
                        "Railway database variables are missing.");
            }

            url = "jdbc:mysql://" + host + ":" + port + "/" + database
                    + "?sslMode=REQUIRED";
        } else {
            url = "jdbc:mysql://localhost:3306/project_db";
            username = "root";
            password = System.getenv("RECIPENEST_DB_PASSWORD");
        }

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            throw new SQLException("Database credentials are missing.");
        }

        return DriverManager.getConnection(url, username, password);
    }
}
