
package com.recipenest.servlet;

import com.recipenest.dao.RatingDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/rate")
public class RatingServlet extends HttpServlet {

    private final RatingDAO ratingDAO = new RatingDAO();

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

        String recipeIdText = request.getParameter("recipeId");
        String ratingText = request.getParameter("rating");

        try {
            int recipeId = Integer.parseInt(recipeIdText);
            int rating = Integer.parseInt(ratingText);

            if (recipeId <= 0 || rating < 1 || rating > 5) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Choose a rating from 1 to 5."
                );
                return;
            }

            int userId = (Integer) session.getAttribute("userId");

            ratingDAO.addOrUpdateRating(userId, recipeId, rating);

            response.sendRedirect(
                    request.getContextPath() + "/recipe?id=" + recipeId
            );

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid recipe ID or rating."
            );
        } catch (SQLException e) {
            throw new ServletException(
                    "Unable to save rating.", e
            );
        }
    }
}
