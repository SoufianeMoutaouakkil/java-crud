<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
tagdir="/WEB-INF/tags" prefix="ctag" %>

<ctag:layout title="Tasks List">
  <h1>Tasks List</h1>
  <p class="alert mt-2" style="display: none" id="form-info"></p>
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
          <c:set
            var="isChecked"
            value="${task.status == 'done' ? 'checked' : ''}"
          />
          <div class="form-check form-switch">
            <input
              class="form-check-input status-task"
              data-id="${task.id}"
              type="checkbox"
              ${isChecked}
            />
          </div>
        </td>
        <td class="col">
          <i
            class="fas fa-edit cursor-pointer edit-task"
            data-id="${task.id}"
          ></i>
          <i
            class="fas fa-trash cursor-pointer delete-task"
            data-id="${task.id}"
          ></i>
        </td>
      </tr>
    </c:forEach>
  </table>

  <script src="/js/tasks/tasks.js" type="module"></script>
</ctag:layout>
