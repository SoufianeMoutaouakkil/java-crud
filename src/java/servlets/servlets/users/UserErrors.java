package servlets.servlets.users;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet(name = "UserErrors", urlPatterns = { "/errors/user" })
public class UserErrors extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String error = request.getParameter("error");
        String id = request.getParameter("id");
        request.setAttribute("id", id);

        if (error.equals("not_found")) {
            request.setAttribute("error", "User not found with id: " + id);
        } else if (error.equals("invalid_id")) {
            request.setAttribute("error", "Invalid user id: " + id);
        } else if (error.equals("unauthorized")) {
            request.setAttribute("error", "Unauthorized access to user with id: " + id);
        } else {
            request.setAttribute("error", "Unknown error");
        }
        request.setAttribute("backUrl", "/users");
        request.getRequestDispatcher("/jsp/errors/record_error.jsp").forward(request, response);
    }
}
