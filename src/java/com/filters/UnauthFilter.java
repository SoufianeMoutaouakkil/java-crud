package com.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import app.entities.User;
import app.utils.Logger;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/login", "/register" })
public class UnauthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the user is authenticated
        if (isAuthenticated(httpRequest)) {
            httpResponse.sendRedirect("/tasks");
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userString = user != null ? user.toString() : "null";
        Logger.log("AuthFilter isAuthenticated: user", userString);
        return user != null;
    }
}