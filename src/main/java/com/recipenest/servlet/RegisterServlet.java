
package com.recipenest.servlet;

import com.recipenest.dao.UserDAO;
import com.recipenest.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || name.isBlank()
                || email == null || email.isBlank()
                || password == null || password.length() < 8
                || name.trim().length() > 100
                || email.trim().length() > 100
                || password.length() > 72) {

            request.setAttribute("error",
                    "Enter a valid name and email. Password must be 8–72 characters.");
            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
            return;
        }

        User user = new User();
        user.setName(name.trim());
        user.setEmail(email.trim().toLowerCase());
        user.setPassword(password);

        try {
            boolean registered = userDAO.registerUser(user);

            if (registered) {
                response.sendRedirect(
                        request.getContextPath() + "/login?registered=1");
            } else {
                request.setAttribute("error",
                        "Registration failed. Please try again.");
                request.getRequestDispatcher("/register.jsp")
                        .forward(request, response);
            }

        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                request.setAttribute("error",
                        "This email may already be registered.");
                request.getRequestDispatcher("/register.jsp")
                        .forward(request, response);
            } else {
                throw new ServletException(
                        "Unable to register user.", e);
            }
        }
    }
}
