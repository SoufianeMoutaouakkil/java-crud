package servlets.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import app.entities.User;
import app.utils.Logger;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/users/*", "/errors/*", "/logout", "/profile", "/profile/*", "/tasks", "/tasks/*" })
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Initializing AuthFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the user is authenticated
        if (isAuthenticated(httpRequest)) {
            // User is authenticated, proceed with the request
            chain.doFilter(request, response);
        } else {
            // User is not authenticated, redirect to login page
            httpResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        System.out.println("Destroying AuthFilter");
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userString = user != null ? user.toString() : "null";
        Logger.log("AuthFilter isAuthenticated: user", userString);
        return user != null;
    }
}