<%@ page import="app.models.UserModel" %>
<%@ page import="app.entities.User" %>
<%@ page import="java.util.List" %>

<%
    List<User> users = new UserModel().getAll();
    request.setAttribute("users", users);
%>