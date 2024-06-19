package servlets.servlets.tasks;

import app.models.TaskModel;
import app.utils.Logger;
import app.entities.Task;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "Task", urlPatterns = { "/tasks/edit/*" })
public class TaskEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestPath = request.getPathInfo();
        Logger.log("TaskDetail doGet: requestPath", requestPath);
        String id = requestPath.substring(requestPath.lastIndexOf("/") + 1);
        Logger.log("TaskDetail doGet: id", id);
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            String message = "Invalid task id";
            response.sendRedirect("/errors/task?error=bad_request&message=" + URLEncoder.encode(message, "UTF-8"));
            return;
        }
        Task task = new TaskModel().getById(Integer.parseInt(id));
        if (task == null) {
            response.sendRedirect("/errors/task?error=not_found&taskId=" + id);
            return;
        }
        request.setAttribute("task", task);
        request.getRequestDispatcher("/jsp/tasks/task.jsp").forward(request, response);
    }
}
