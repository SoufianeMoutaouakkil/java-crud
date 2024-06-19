package servlets.servlets.auth;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import app.entities.User;
import app.models.UserModel;

@WebServlet(name = "Register", urlPatterns = { "/register" })
public class Register extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/jsp/auth/register.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("confirm_password", confirmPassword);

        try {
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                request.setAttribute("error", "All fields are required");
            } else if (password.length() < 6 || !password.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match");
            } else {
                User user;
                user = new User(name, email, password);

                UserModel userModel = new UserModel();
                if (userModel.getByEmail(email) != null) {
                    request.setAttribute("error", "User already exists with email: " + email);
                } else {
                    userModel.create(user);
                    request.setAttribute("success", "User created successfully");
                    request.getSession().setAttribute("user", user);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/jsp/auth/register.jsp").forward(request, response);
    }
}
