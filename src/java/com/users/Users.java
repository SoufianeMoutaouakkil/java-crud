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
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@sm.dev");
        try {
            user.setPassword("Password0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("userSample", user);
        request.getRequestDispatcher("jsp/users/usersList.jsp").forward(request, response);
    }
}
