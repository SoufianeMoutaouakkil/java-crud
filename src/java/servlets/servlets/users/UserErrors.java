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
        String id = request.getParameter("userId");
        request.setAttribute("userId", id);

        if (error.equals("not_found")) {
            request.getRequestDispatcher("/jsp/errors/user/not_found.jsp").forward(request, response);
        } else if (error.equals("invalid_id")) {
            request.getRequestDispatcher("/jsp/errors/user/invalid_id.jsp").forward(request, response);
        }
    }
}
