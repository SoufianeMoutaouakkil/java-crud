package servlets.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;

import app.entities.Task;
import app.entities.User;
import app.models.TaskModel;

@WebFilter(urlPatterns = { "/api/tasks/*" }, filterName = "ApiTasksFilter")
public class ApiTasksFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("ApiTasksFilter doFilter");
        String allowedActions = "create|update|delete";
        response.setContentType("application/json");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String infoPath = httpRequest.getPathInfo();
        String method = httpRequest.getMethod();
        if (method.equals("POST") == false && method.equals("PUT") == false && method.equals("DELETE") == false) {
            ((HttpServletResponse) response).setStatus(405);
            response.getWriter().write("{\"message\": \"Method not allowed\"}");
            return;
        }
        // right trim the infoPath
        infoPath = infoPath == null ? null : infoPath.replaceAll("/$", "");

        // check if the URL is valid: /api/tasks/{action}/{id} or /api/tasks/create
        if (infoPath == null || infoPath.matches("^/\\w+(/\\d+)?$") == false) {
            ((HttpServletResponse) response).setStatus(400);
            response.getWriter().write("{\"message\": \"Invalid URL\"}");
            return;
        }
        // split the infoPath into parts
        String[] parts = infoPath.split("/");
        String action = parts[1];
        int id = 0;
        try {
            if (parts.length > 2) {
                id = Integer.parseInt(parts[2]);
            }
        } catch (Exception e) {
            ((HttpServletResponse) response).setStatus(400);
            response.getWriter().write("{\"message\": \"Invalid ID: " + parts[2] + "\"}");
            return;
        }

        if (action.matches(allowedActions) == false) {
            ((HttpServletResponse) response).setStatus(400);
            response.getWriter().write("{\"message\": \"Invalid action: " + action + "\"}");
            return;
        }

        request.setAttribute("action", action);
        // set the id attribute only if it is not null and save it as an integer
        TaskModel tasksModel = new TaskModel();
        User user = (User) request.getAttribute("user");
        Task task = null;
        if (id != 0) {
            task = tasksModel.getById(id);
            if (task == null) {
                ((HttpServletResponse) response).setStatus(404);
                response.getWriter().write("{\"message\": \"Task not found with id: " + id + "\"}");
                return;
            }
            // check authorization
            if (user.getId() != task.getUserId()) {
                ((HttpServletResponse) response).setStatus(403);
                response.getWriter().write("{\"message\": \"Unauthorized access to task with id: " + id + "\"}");
                return;
            }
        } else {
            task = new Task();
            task.setUserId(user.getId());
        }

        request.setAttribute("id", id);
        request.setAttribute("task", task);
        chain.doFilter(request, response);
    }
}
