<%@ page import="app.entities.User" %>
<%
    Object user = request.getSession().getAttribute("user");
    request.setAttribute("user", user);
    if (user == null) {
        response.sendRedirect("/login");
    } else {
        response.sendRedirect("/tasks");
    }
%>