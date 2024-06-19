<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="app.entities.User" %>

<%
    User user = (User) session.getAttribute("user");
    request.setAttribute("isLoggedIn", user != null);
    request.setAttribute("username", user != null ? user.getName() : "");
%>

<header class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/tasks">Task Manager</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <c:choose>
                    <c:when test="${isLoggedIn}">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/tasks">Tasks</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/tasks/new">New Task</a>
                            </li>
                            <%-- profile options as list --%>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    ${username}
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li>
                                        <a class="dropdown-item" href="/profile">Profile</a>
                                    </li>
                                    <%-- red item --%>
                                    <li class="alert-danger">
                                        <a class="dropdown-item" href="/logout">Logout</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/login">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/register">Register</a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>
