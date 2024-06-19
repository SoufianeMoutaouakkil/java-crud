<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ctag" %>

<ctag:layout title="Users List">
    <h1>Users List</h1>
    <table border="1" class="table">
        <tr class="table-dark">
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr class="table-light">
                <td class="table-light">${user.name}</td>
                <td class="table-light">${user.email}</td>
                <td class="table-light">
                    <a href="/users/edit/${user.id}">Edit</a>
                    <a href="/users/delete/${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</ctag:layout>
