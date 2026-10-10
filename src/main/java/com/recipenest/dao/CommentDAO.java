
package com.recipenest.dao;

import com.recipenest.model.Comment;
import com.recipenest.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public boolean addComment(Comment comment) throws SQLException {

        String sql = "INSERT INTO comments "
                   + "(user_id, recipe_id, comment_text) "
                   + "VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, comment.getUserId());
            statement.setInt(2, comment.getRecipeId());
            statement.setString(3, comment.getCommentText());

            return statement.executeUpdate() > 0;
        }
    }

    public List<Comment> getCommentsByRecipe(int recipeId)
            throws SQLException {

        List<Comment> comments = new ArrayList<>();

        String sql = "SELECT * FROM comments "
                   + "WHERE recipe_id = ? "
                   + "ORDER BY created_at DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, recipeId);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Comment comment = new Comment();

                    comment.setCommentId(
                            resultSet.getInt("comment_id"));
                    comment.setUserId(
                            resultSet.getInt("user_id"));
                    comment.setRecipeId(
                            resultSet.getInt("recipe_id"));
                    comment.setCommentText(
                            resultSet.getString("comment_text"));

                    java.sql.Timestamp timestamp =
                            resultSet.getTimestamp("created_at");

                    if (timestamp != null) {
                        comment.setCreatedAt(
                                timestamp.toLocalDateTime());
                    }

                    comments.add(comment);
                }
            }
        }

        return comments;
    }
}
