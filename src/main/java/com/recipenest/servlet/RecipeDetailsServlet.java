
package com.recipenest.servlet;

import com.recipenest.dao.RecipeDAO;
import com.recipenest.dao.CommentDAO;
import com.recipenest.dao.RatingDAO;
import com.recipenest.model.Recipe;
import com.recipenest.model.Comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/recipe")
public class RecipeDetailsServlet extends HttpServlet {

    private final RecipeDAO recipeDAO = new RecipeDAO();
    private final CommentDAO commentDAO = new CommentDAO();
    private final RatingDAO ratingDAO = new RatingDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null || id.isBlank()) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Recipe ID is required."
            );
            return;
        }

        try {
            int recipeId = Integer.parseInt(id);

            Recipe recipe = recipeDAO.getRecipeById(recipeId);

            if (recipe == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Recipe not found."
                );
                return;
            }

            List<Comment> comments =
                    commentDAO.getCommentsByRecipe(recipeId);

            double averageRating =
                    ratingDAO.getAverageRating(recipeId);

            int ratingCount =
                    ratingDAO.getRatingCount(recipeId);

            request.setAttribute("recipe", recipe);
            request.setAttribute("comments", comments);
            request.setAttribute("averageRating", averageRating);
            request.setAttribute("ratingCount", ratingCount);

            request.getRequestDispatcher("/recipe-details.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid recipe ID."
            );
        } catch (SQLException e) {
            throw new ServletException(
                    "Unable to load recipe details.", e
            );
        }
    }
}
