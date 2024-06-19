package com.servlets.auth;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import app.entities.User;
import app.models.UserModel;
import app.utils.Logger;

@WebServlet(name = "Login", urlPatterns = { "/login" })
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/jsp/auth/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserModel userModel = new UserModel();
        User user = userModel.getByEmail(email);

        if (user == null) {
            request.setAttribute("error", "User not found with email: " + email);
        } else if (!user.checkPassword(password)) {
            request.setAttribute("error", "Invalid password");
        } else {
            Logger.log("Login", "User logged in: " + user.toString());
            request.getSession().setAttribute("user", user);
            request.setAttribute("success", "Login successful");

        }
        request.getRequestDispatcher("/jsp/auth/login.jsp").forward(request, response);
    }
}
