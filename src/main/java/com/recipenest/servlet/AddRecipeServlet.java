
package com.recipenest.servlet;

import com.recipenest.dao.RecipeDAO;
import com.recipenest.model.Recipe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-recipe")
public class AddRecipeServlet extends HttpServlet {

    private final RecipeDAO recipeDAO = new RecipeDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");
        String category = request.getParameter("category");

        if (title == null || title.isBlank()
                || ingredients == null || ingredients.isBlank()
                || instructions == null || instructions.isBlank()
                || title.trim().length() > 150
                || (description != null && description.length() > 500)
                || ingredients.length() > 10000
                || instructions.length() > 20000
                || (category != null && category.length() > 50)) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Please provide valid recipe details."
            );
            return;
        }

        Recipe recipe = new Recipe();
        recipe.setTitle(title.trim());
        recipe.setDescription(
                description == null ? "" : description.trim());
        recipe.setIngredients(ingredients.trim());
        recipe.setInstructions(instructions.trim());
        recipe.setCategory(
                category == null ? "" : category.trim());

        int userId = (Integer) session.getAttribute("userId");
        recipe.setUserId(userId);

        try {
            recipeDAO.addRecipe(recipe);
            response.sendRedirect(
                    request.getContextPath() + "/home?added=1");
        } catch (SQLException e) {
            throw new ServletException(
                    "Unable to save the recipe.", e);
        }
    }
}
