package com.recipenest.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/project_db";

    private static final String USERNAME = "root";

    private static final String PASSWORD =
            System.getenv("RECIPENEST_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new SQLException(
                    "RECIPENEST_DB_PASSWORD environment variable is missing."
            );
        }

        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);

        com.mysql.cj.jdbc.Driver driver =
                new com.mysql.cj.jdbc.Driver();

        Connection connection = driver.connect(URL, properties);

        if (connection == null) {
            throw new SQLException(
                    "MySQL driver rejected the database URL."
            );
        }

        return connection;
    }
}