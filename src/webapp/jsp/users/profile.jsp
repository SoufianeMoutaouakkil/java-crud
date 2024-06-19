<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ctag" %>
<%@ page import="app.entities.User" %>

<%
    User user = (User) request.getSession().getAttribute("user");
    request.setAttribute("user", user);
%>

<ctag:layout title="Profile">
    <h1>Profile</h1>
    <%-- block as card for info --%>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title d-flex justify-content-between cursor-pointer">
                <span>
                    <i class="fas fa-user"></i> Name: ${user.name}
                </span>
                <div class="text-primary cursor-pointer" onclick="ToggleFormName()" id="edit-name">
                    <i class="fas fa-edit"></i>
                </div>
            </h5>
            <h6 class="card-subtitle mb-2 text-muted">
                <i class="fas fa-envelope"></i> Email: ${user.email}
            </h6>
        </div>
    </div>
    
    <!-- Name and Email edit form -->
    <div class="card my-3" id="form-name" style="display: none;">
        <div class="card-header">
            <h5 class="card-title d-flex justify-content-between">
                <span>
                    <i class="fas fa-user"></i> Edit Name and Email
                </span>
                <div class="text-primary cursor-pointer" onclick="ToggleFormName('close')">
                    <i class="fas fa-times"></i>
                </div>
            </h5>
        </div>
        <div class="card-body">
            <form action="/profile/edit" method="post" class="form" id="form-name">
                <div class="form-group">
                    <label for="name" class="form-label">Name:</label>
                    <input type="text" id="name" name="name" class="form-control" value="${user.name}">
                </div>
                <div class="form-group mt-2">
                    <label for="email" class="form-label">Email:</label>
                    <input type="text" id="email" name="email" class="form-control" value="${user.email}">
                </div>
                <div class="form-group mt-2">
                    <button
                        type="submit"
                        class="btn btn-primary"
                        id="save-name-button"
                        disabled
                    >Save</button>
                </div>
            </form>
            <p class="text-muted mt-2">* Click on the 'x' icon to cancel</p>
            <p class="alert alert-warning mt-2" style="display: none;" id="name-error-message"></p>
        </div>
    </div>

    <script>
        // add event listener
        document.getElementById('name').addEventListener('input', function() {
            activeSaveButton();
        });
        document.getElementById('email').addEventListener('input', function() {
            activeSaveButton();
        });
        document.getElementById('form-name').addEventListener('submit', function(event) {
            event.preventDefault();
            errorP = document.getElementById('name-error-message');
            errorP.style.display = "none";
            errorP.innerHTML = "";

            const form = event.target;
            const data = new FormData(form);
            // convert FormData to JSON
            const json = {};
            data.forEach((value, key) => { json[key] = value });

            fetch(form.action, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(json)
            })
            .then(response => {
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    ToggleFormName('close');
                    alert(data.message);
                } else {
                    errorP.style.display = "block";
                    errorP.innerHTML = "Error: " + data.message;
                }
            })
            .catch(error => {
                errorP.style.display = "block";
                errorP.innerHTML = "Error: " + error;
            });
        });
        function navTo(path) {
            window.location.href = path;
        }
        function ToggleFormName(action = "open") {
            var form = document.getElementById('form-name');
            if (action === "open") {
                form.style.display = "block";
                document.getElementById('edit-name').style.display = "none";
            } else {
                form.style.display = "none";
                document.getElementById('edit-name').style.display = "block";
            }
        }
        
        function activeSaveButton() {
            const oldName = '${user.name}';
            const newName = document.getElementById('name').value;
            const oldEmail = '${user.email}';
            const newEmail = document.getElementById('email').value;
            var button = document.getElementById('save-name-button');
            if (oldName === newName && oldEmail === newEmail) {
                button.disabled = true;
            } else {
                button.disabled = false;
            }
        }
    </script>
</ctag:layout>
