
package com.recipenest.dao;

import com.recipenest.model.Recipe;
import com.recipenest.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    public List<Recipe> getAllRecipes() throws SQLException {

        List<Recipe> recipes = new ArrayList<>();

        String sql = "SELECT * FROM recipes ORDER BY created_at DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Recipe recipe = new Recipe();

                recipe.setRecipeId(resultSet.getInt("recipe_id"));
                recipe.setUserId(resultSet.getInt("user_id"));
                recipe.setTitle(resultSet.getString("title"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setInstructions(resultSet.getString("instructions"));
                recipe.setCategory(resultSet.getString("category"));

                java.sql.Timestamp timestamp =
                        resultSet.getTimestamp("created_at");

                if (timestamp != null) {
                    recipe.setCreatedAt(timestamp.toLocalDateTime());
                }

                recipes.add(recipe);
            }
        }

        return recipes;
    }

    public Recipe getRecipeById(int recipeId) throws SQLException {

    String sql = "SELECT * FROM recipes WHERE recipe_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, recipeId);

        try (ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                Recipe recipe = new Recipe();

                recipe.setRecipeId(resultSet.getInt("recipe_id"));
                recipe.setUserId(resultSet.getInt("user_id"));
                recipe.setTitle(resultSet.getString("title"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setInstructions(resultSet.getString("instructions"));
                recipe.setCategory(resultSet.getString("category"));

                java.sql.Timestamp timestamp =
                        resultSet.getTimestamp("created_at");

                if (timestamp != null) {
                    recipe.setCreatedAt(timestamp.toLocalDateTime());
                }

                return recipe;
            }
        }
    }

    return null;
}
     
    public boolean addRecipe(Recipe recipe) throws SQLException {

        String sql = "INSERT INTO recipes "
                   + "(user_id, title, description, ingredients, "
                   + "instructions, category) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, recipe.getUserId());
            statement.setString(2, recipe.getTitle());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getIngredients());
            statement.setString(5, recipe.getInstructions());
            statement.setString(6, recipe.getCategory());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteRecipe(int recipeId, int userId)
            throws SQLException {

        String sql = "DELETE FROM recipes "
                   + "WHERE recipe_id = ? AND user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, recipeId);
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;
        }
    }
}
