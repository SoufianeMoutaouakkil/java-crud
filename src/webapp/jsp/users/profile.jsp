<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
tagdir="/WEB-INF/tags" prefix="ctag" %> <%@ page import="app.entities.User" %>

<ctag:layout title="Profile">
  <h1>Profile</h1>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title d-flex justify-content-between cursor-pointer">
        <span> <i class="fas fa-user"></i> Name: ${user.name} </span>
        <div class="text-primary cursor-pointer" id="forms-trigger">
          <i class="fas fa-edit" id="edit-name-icon"></i>
          <i class="fas fa-lock" id="edit-password-icon"></i>
        </div>
      </h5>
      <h6 class="card-subtitle mb-2 text-muted">
        <i class="fas fa-envelope"></i> Email: ${user.email}
      </h6>
    </div>
  </div>

  <!-- Name and Email edit form -->
  <div class="card my-3" id="form-name-card" style="display: none">
    <div class="card-header">
      <h5 class="card-title d-flex justify-content-between">
        <span> <i class="fas fa-user"></i> Edit Name and Email </span>
        <div class="text-primary cursor-pointer">
          <i class="fas fa-times" id="close-name-form"></i>
        </div>
      </h5>
    </div>
    <div class="card-body">
      <form action="/profile/edit" method="post" class="form" id="form-name">
        <div class="form-group">
          <label for="name" class="form-label">Name:</label>
          <input
            type="text"
            id="name"
            name="name"
            class="form-control"
            value="${user.name}"
          />
        </div>
        <div class="form-group mt-2">
          <label for="email" class="form-label">Email:</label>
          <input
            type="text"
            id="email"
            name="email"
            class="form-control"
            value="${user.email}"
          />
        </div>
        <div class="form-group mt-2">
          <button
            type="submit"
            class="btn btn-primary mt-2"
            id="save-name-button"
            disabled
          >
            Save
          </button>
        </div>
      </form>
      <p class="text-muted mt-2">* Click on the 'x' icon to cancel</p>
    </div>
  </div>

  <div class="card my-3" id="form-password-card" style="display: none">
    <div class="card-body">
      <h5 class="card-title d-flex justify-content-between">
        <span> <i class="fas fa-lock"></i> Change Password </span>
        <div class="text-primary cursor-pointer">
          <i class="fas fa-times" id="close-password-form"></i>
        </div>
      </h5>
      <form
        action="/profile/change-password"
        method="post"
        class="form"
        id="form-password"
      >
        <div class="form-group">
          <label for="old-password" class="form-label">Old Password:</label>
          <input
            type="password"
            id="old-password"
            name="oldPassword"
            class="form-control"
          />
        </div>
        <div class="form-group">
          <label for="new-password" class="form-label">New Password:</label>
          <input
            type="password"
            id="new-password"
            name="newPassword"
            class="form-control"
          />
        </div>
        <div class="form-group">
          <label for="confirm-password" class="form-label"
            >Confirm Password:</label
          >
          <input
            type="password"
            id="confirm-password"
            name="confirmPassword"
            class="form-control"
          />
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-primary mt-2">Save</button>
        </div>
      </form>
      <p class="text-muted mt-2">* Click on the 'x' icon to cancel</p>
    </div>
  </div>
  <p
    class="alert mt-2"
    style="display: none"
    id="form-info"
  ></p>
  <script type="module" src="/js/users/profile.js"></script>
</ctag:layout>
