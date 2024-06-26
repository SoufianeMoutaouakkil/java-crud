package servlets.servlets.tasks;

import app.models.TaskModel;
import app.entities.Task;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import app.utils.Logger;
import app.entities.User;

@WebServlet(name = "TasksServlet", urlPatterns = { "/tasks" })
public class TasksServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Logger.log("Tasks doGet", request.getPathInfo());
        User user = (User) request.getSession().getAttribute("user");
        List<Task> tasks = new TaskModel().getByUserId(user.getId());
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/jsp/tasks/tasks.jsp").forward(request, response);
    }
}
