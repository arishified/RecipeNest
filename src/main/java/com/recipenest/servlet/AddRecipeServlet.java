package com.recipenest.servlet;

import com.recipenest.dao.RecipeDAO;
import com.recipenest.model.Recipe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-recipe")
public class AddRecipeServlet extends HttpServlet {

    private final RecipeDAO recipeDAO = new RecipeDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");
        String category = request.getParameter("category");

        if (title == null || title.isBlank()
                || ingredients == null || ingredients.isBlank()
                || instructions == null || instructions.isBlank()) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Title, ingredients and instructions are required."
            );
            return;
        }

        Recipe recipe = new Recipe();
        recipe.setTitle(title.trim());
        recipe.setDescription(description == null ? "" : description.trim());
        recipe.setIngredients(ingredients.trim());
        recipe.setInstructions(instructions.trim());
        recipe.setCategory(category == null ? "" : category.trim());

        // Demo project: associate the new recipe with the first sample user.
        recipe.setUserId(1);

        try {
            recipeDAO.addRecipe(recipe);
            response.sendRedirect(
                    request.getContextPath() + "/home?added=1"
            );
        } catch (Exception e) {
            throw new ServletException("Unable to save the recipe.", e);
        }
    }
}