
package com.recipenest.servlet;

import com.recipenest.dao.CommentDAO;
import com.recipenest.model.Comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {

    private final CommentDAO commentDAO = new CommentDAO();

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

        String recipeIdText = request.getParameter("recipeId");
        String commentText = request.getParameter("commentText");

        try {
            int recipeId = Integer.parseInt(recipeIdText);

            if (recipeId <= 0 || commentText == null
                    || commentText.isBlank()
                    || commentText.trim().length() > 500) {

                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Comment must contain 1–500 characters."
                );
                return;
            }

            int userId = (Integer) session.getAttribute("userId");

            Comment comment = new Comment();
            comment.setUserId(userId);
            comment.setRecipeId(recipeId);
            comment.setCommentText(commentText.trim());

            if (commentDAO.addComment(comment)) {
                response.sendRedirect(
                        request.getContextPath()
                                + "/recipe?id=" + recipeId);
            } else {
                response.sendError(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Unable to save comment."
                );
            }

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid recipe ID."
            );
        } catch (SQLException e) {
            throw new ServletException(
                    "Unable to save comment.", e);
        }
    }
}
