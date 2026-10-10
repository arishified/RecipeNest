package com.recipenest.servlet;

import com.recipenest.dao.RecipeDAO;
import com.recipenest.model.Recipe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/recipe")
public class RecipeDetailsServlet extends HttpServlet {

    private final RecipeDAO recipeDAO = new RecipeDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null || id.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Recipe ID is required.");
            return;
        }

        try {
            int recipeId = Integer.parseInt(id);
            Recipe recipe = recipeDAO.getRecipeById(recipeId);

            if (recipe == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "Recipe not found.");
                return;
            }

            request.setAttribute("recipe", recipe);
            request.getRequestDispatcher("/recipe-details.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid recipe ID.");
        } catch (SQLException e) {
            throw new ServletException("Unable to load recipe details.", e);
        }
    }
}
