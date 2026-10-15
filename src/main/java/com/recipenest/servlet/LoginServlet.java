
package com.recipenest.servlet;

import com.recipenest.dao.UserDAO;
import com.recipenest.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        if ("1".equals(request.getParameter("registered"))) {
            request.setAttribute("message",
                    "Account created successfully! Please log in.");
        }

        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isBlank()
                || password == null || password.isEmpty()) {
            request.setAttribute("error",
                    "Enter your email and password.");
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
            return;
        }

        try {
            User user = userDAO.getUserByEmail(email);

            if (user == null
                    || !userDAO.verifyPassword(
                            password, user.getPassword())) {
                request.setAttribute("error",
                        "Invalid email or password.");
                request.getRequestDispatcher("/login.jsp")
                        .forward(request, response);
                return;
            }

            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userRole", user.getRole());
            session.setMaxInactiveInterval(30 * 60);

            response.sendRedirect(
                    request.getContextPath() + "/home");

        } catch (SQLException e) {
            throw new ServletException(
                    "Unable to log in.", e);
        }
    }
}
