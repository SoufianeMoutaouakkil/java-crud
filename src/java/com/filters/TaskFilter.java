package com.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import app.entities.Task;
import app.entities.User;
import app.models.TaskModel;
import app.utils.Logger;

@jakarta.servlet.annotation.WebFilter(urlPatterns = { "/tasks/edit/*", "/tasks/delete/*" })
public class TaskFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        User user = getUser(httpRequest);
        if (user != null) {
            TaskModel taskModel = new TaskModel();
            int taskId = -1;
            try {
                taskId = Integer.parseInt(httpRequest.getRequestURI().split("/")[3]);
            } catch (Exception err) {
                Logger.log("TaskFilter error", err.getMessage());
                // encode the message to be URL safe
                httpResponse.sendRedirect("/tasks/error?id=" + taskId +"&type=invalid_id");
            }
            Task task = taskModel.getById(taskId);
            if (task == null) {
                Logger.log("TaskFilter error", "Task not found with id: " + taskId);
                httpResponse.sendRedirect("/tasks/error?id=" + taskId +"&type=not_found");
            }

            int userId = user.getId();
            boolean isOwner = userId == task.getUserId();

            if (isOwner) {
                chain.doFilter(request, response);
            } else {
                Logger.log("TaskFilter error", "Unauthorized access to task with id: " + taskId);
                httpResponse.sendRedirect("/tasks/error?id=" + taskId +"&type=unauthorized");
            }
        } else {
            httpResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        System.out.println("Destroying TaskFilter");
    }

    private User getUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userString = user != null ? user.toString() : "null";
        Logger.log("TaskFilter isAuthenticated: user", userString);
        return user;
    }
}
