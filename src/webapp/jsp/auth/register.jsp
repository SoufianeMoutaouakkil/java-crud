<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ctag" %>


<ctag:layout title="Regiser">
    <h1>Register Form</h1>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
        <meta http-equiv="refresh" content="2;url=/tasks">
    </c:if>
    <c:if test="${empty success}">
        <form action="/register" method="post" class="form">
            <div class="form-group">
                <label for="name" class="form-label">Fullname:</label>
                <input type="text" id="name" name="name" class="form-control" value="${name}">
            </div>
            <div class="form-group">
                <label for="email" class="form-label">Email:</label>
                <input type="text" id="email" name="email" class="form-control" value="${email}">
            </div>
            <div class="form-group">
                <label for="password" class="form-label">Password:</label>
                <input type="password" id="password" name="password" class="form-control" value="${password}">
            </div>
            <div class="form-group">
                <label for="confirmPassword" class="form-label">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirm_password" class="form-control" value="${confirm_password}">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Register</button>
            </div>
        </form>
    </c:if>
</ctag:layout>
