
package com.recipenest.dao;

import com.recipenest.model.User;
import com.recipenest.util.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean registerUser(User user) throws SQLException {

        String sql = "INSERT INTO users (name, email, password, role) "
                   + "VALUES (?, ?, ?, ?)";

        String hashedPassword = BCrypt.hashpw(
                user.getPassword(),
                BCrypt.gensalt(12)
        );

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getName().trim());
            statement.setString(2, user.getEmail().trim().toLowerCase());
            statement.setString(3, hashedPassword);
            statement.setString(4, "USER");

            return statement.executeUpdate() > 0;
        }
    }

    public User getUserByEmail(String email) throws SQLException {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email.trim().toLowerCase());

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    User user = new User();

                    user.setUserId(resultSet.getInt("user_id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));

                    // This contains the BCrypt hash, not the original password.
                    user.setPassword(resultSet.getString("password"));

                    user.setRole(resultSet.getString("role"));

                    return user;
                }
            }
        }

        return null;
    }

    public boolean verifyPassword(String plainPassword,
                                  String hashedPassword) {

        if (plainPassword == null || hashedPassword == null
                || hashedPassword.isBlank()) {
            return false;
        }

        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
