<%@ page import="app.models.UserModel" %>
<%@ page import="app.entities.User" %>
<%@ page import="java.util.List" %>

<%!
    String error = "";
    User addUser(String name, String email, String password) {
        try {
            UserModel userModel = new UserModel();
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            // return userModel.create(user);
            return user;
        } catch (Exception e) {
            error = e.getMessage();
            return null;
        }
    };
%>

<%
    String method = request.getMethod();
    if (error != null && !error.isEmpty() && method.equals("POST")) {
        request.setAttribute("error", error);
    } else {
        request.setAttribute("error", "");
    }

    if (method.equals("POST")) {
        out.println("POST");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        out.println("Recieved: " + name + " " + email + " " + password);
        if (name != null && email != null && password != null) {
            User user = addUser(name, email, password);
            if (user != null) {
                request.setAttribute("error", "User created: " + user.getName());
            } else {
                request.setAttribute("error", error);
            }
        } else {
            request.setAttribute("error", "User not created! Missing parameters!");
        }
    } else if (method.equals("GET")) {
        out.println("GET");
    }

%>

<jsp:include page="/jsp/users/user-form.jsp" />