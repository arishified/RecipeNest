
package com.recipenest.dao;

import com.recipenest.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RatingDAO {

    public boolean addOrUpdateRating(
            int userId, int recipeId, int rating) throws SQLException {

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5.");
        }

        String sql = "INSERT INTO ratings (user_id, recipe_id, rating) "
                   + "VALUES (?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE rating = VALUES(rating)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, recipeId);
            statement.setInt(3, rating);

            return statement.executeUpdate() > 0;
        }
    }
}
