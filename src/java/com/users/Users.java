package com.users;

import app.models.UserModel;
import app.entities.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Users", urlPatterns = { "/users" })
public class Users extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> users = new UserModel().getAll();
        request.setAttribute("users", users);

        request.getRequestDispatcher("jsp/users/usersList.jsp").forward(request, response);
    }
}
