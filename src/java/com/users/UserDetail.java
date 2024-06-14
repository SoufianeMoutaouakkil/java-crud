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

@WebServlet(name = "UserDetail", urlPatterns = { "/detailuser/*" })
public class UserDetail extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> users = new UserModel().getAll();
        String path = request.getServletPath();
        String id = request.getParameter("id");
        User user = new UserModel().getById(Integer.parseInt(id));
        request.setAttribute("users", users);
        request.setAttribute("path", path);
        request.setAttribute("userSample", user);
        request.setAttribute("pi", "3.14159265359"); // Add this line to the original code
        request.getRequestDispatcher("user.jsp").forward(request, response);

    }
}
