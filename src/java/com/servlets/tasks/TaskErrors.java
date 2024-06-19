package com.servlets.tasks;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet(name = "TaskErrors", urlPatterns = { "/errors/task" })
public class TaskErrors extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String error = request.getParameter("type");
        String id = request.getParameter("id");

        if (error.equals("not_found")) {
            request.setAttribute("error", "Task not found with id: " + id);
        } else if (error.equals("invalid_id")) {
            request.setAttribute("error", "Invalid task id: " + id);
        } else if (error.equals("unauthorized")) {
            request.setAttribute("error", "Unauthorized access to task with id: " + id);
        } else {
            request.setAttribute("error", "Unknown error");
        }
        request.setAttribute("backUrl", "/tasks");
        request.getRequestDispatcher("/jsp/errors/record_error.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
