package servlets.servlets.users;

import app.models.UserModel;
import app.utils.Logger;
import app.entities.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "User", urlPatterns = { "/users/*" })
public class UserDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestPath = request.getPathInfo();
        Logger.log("UserDetail doGet: requestPath", requestPath);
        String id = requestPath.substring(requestPath.lastIndexOf("/") + 1);
        Logger.log("UserDetail doGet: id", id);
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            String message = "Invalid user id";
            response.sendRedirect("/errors/user?error=bad_request&message=" + URLEncoder.encode(message, "UTF-8"));
            return;
        }
        User user = new UserModel().getById(Integer.parseInt(id));
        if (user == null) {
            response.sendRedirect("/errors/user?error=not_found&userId=" + id);
            return;
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/users/user.jsp").forward(request, response);
    }
}
