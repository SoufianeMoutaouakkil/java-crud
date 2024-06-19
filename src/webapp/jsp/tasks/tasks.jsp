<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ctag" %>


<ctag:layout title="Tasks List">
    <h1>Tasks List</h1>
    <table border="1" class="table">
        <tr class="table-dark">
            <th class="col">Name</th>
            <th class="col">Description</th>
            <th class="col">Status</th>
            <th class="col">Actions</th>
        </tr>
        <c:forEach var="task" items="${tasks}">
            <tr class="table-light">
                <td class="col">${task.name}</td>
                <td class="col">${task.description}</td>
                <td class="col">
                <%-- as radio button with bs5 --%>
                <div class="form-check form-switch">
                    <input
                        class="form-check-input"
                        type="checkbox"
                        <c:if test="${task.status == 'done'}">checked</c:if>
                    />
                </div>
                </td>
                <td class="col">
                    <a href="/tasks/edit/${task.id}">Edit</a>
                    <a href="/tasks/delete/${task.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</ctag:layout>
