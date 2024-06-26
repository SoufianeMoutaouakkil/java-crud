package servlets.servlets.tasks;

import app.models.TaskModel;
import app.entities.Task;
import app.utils.Logger;
import app.utils.JsonData;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet(name = "ApiTasksServlet", urlPatterns = { "/api/tasks/*" })
public class ApiTasksServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        int id = (int) request.getAttribute("id");
        Task task = (Task) request.getAttribute("task");


        if (action.equals("delete")) {
            Logger.log("TaskApi doPost: delete", id + "");
            new TaskModel().delete(id);
            response.getWriter().write("{\"status\": \"success\", \"message\": \"Task deleted\"}");
        } else if (action.equals("update")) {
            JsonData data = (JsonData) request.getAttribute("data");
            String name = data.getString("name");
            String description = data.getString("description");
            String status = data.getString("status");
            task.setName(name);
            task.setDescription(description);
            task.setStatus(status);
            new TaskModel().update(task);

            response.getWriter().write("{\"status\": \"success\", \"message\": \"Task updated\"}");
        } else if (action.equals("create")) {
            JsonData data = (JsonData) request.getAttribute("data");
            String name = data.getString("name");
            task.setName(name);
            new TaskModel().create(task);

            response.getWriter().write("{\"status\": \"success\", \"message\": \"Task created\"}");
        } else {
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Invalid action " + action + "\"}");
        }
    }
}
