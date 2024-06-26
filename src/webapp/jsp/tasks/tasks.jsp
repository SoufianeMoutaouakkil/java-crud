<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
tagdir="/WEB-INF/tags" prefix="ctag" %>

<ctag:layout title="Tasks List">
  <h1>Task List</h1>
  <!-- column direction  -->
  <div class="d-flex flex-column justify-content-between flex-grow-1">
    <div class="tasks-container">
      <table border="1" class="table" id="tasks-table">
        <tr class="table-dark">
          <th class="col">Name</th>
          <th class="col">Description</th>
          <th class="col">Status</th>
          <th class="col">Actions</th>
        </tr>
        <c:forEach var="task" items="${tasks}">
          <tr class="table-light">
            <td class="col task-name-td" data-id="${task.id}">${task.name}</td>
            <td class="col task-description-td" data-id="${task.id}">
              ${task.description}
            </td>
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
                class="fas fa-trash cursor-pointer delete-task ms-2 text-danger"
                data-id="${task.id}"
                type="button"
              ></i>
            </td>
          </tr>
        </c:forEach>
      </table>
      <p class="alert mt-2" style="display: none" id="form-info"></p>
    </div>
    <div class="form-container">
      <jsp:include page="/jsp/tasks/add-task.jsp" />
    </div>
  </div>
  <script src="/js/tasks/tasks.js" type="module"></script>
</ctag:layout>
