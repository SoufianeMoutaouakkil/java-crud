package servlets.servlets.users;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;

import java.io.IOException;

import app.entities.User;
import app.models.UserModel;
import app.utils.Logger;

@WebServlet(name = "Profile", urlPatterns = { "/profile", "/profile/change-password", "/profile/edit",
        "/profile/delete" })
public class Profile extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/users/profile.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        UserModel userModel = new UserModel();
        String path = request.getServletPath();
        response.setContentType("application/json");
        JsonObject data = (JsonObject) request.getAttribute("data");
        try {
            if (path.equals("/profile/edit")) {
                String email = data.get("email").getAsString();
                String name = data.get("name").getAsString();
                Logger.log("Edit profile", "Email: " + email + ", Name: " + name);
                if (!email.equals(user.getEmail()) && userModel.getByEmail(email) != null) {
                    // send json response with 400 status code
                    System.out.println("User already exists with email: " + email);
                    System.out.println("User email: " + user.getEmail());
                    response.setStatus(400);
                    response.getWriter().write("{\"message\": \"User already exists with email: " + email + "\"}");
                } else {
                    user.setEmail(email);
                    user.setName(name);
                    userModel.update(user);
                    request.getSession().setAttribute("user", user);
                    response.getWriter().write("{\"message\": \"User updated successfully\"}");
                }
            } else if (path.equals("/profile/change-password")) {
                response.setContentType("application/json");
                String password = request.getParameter("password");
                user.setPassword(password);
                userModel.update(user);
                response.getWriter().write("{\"message\": \"Password updated successfully\"}");
                return;
            } else if (path.equals("/profile/delete")) {
                userModel.delete(user.getId());
                request.getSession().invalidate();
                response.sendRedirect("/login");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            // get message but replace double quotes with single quotes
            String message = e.getMessage().replace("\"", "'");
            response.getWriter().write("{\"message\": \"Internal Server Error: " + message + "\"}");
        }
    }

}
