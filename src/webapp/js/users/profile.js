import { jsonFromForm } from "../common/form.js";
import calert from "../common/alert.js";
import { post } from "../common/api.js";

document.addEventListener("DOMContentLoaded", function () {
  console.log("profile.js ready");
  document.getElementById("name").addEventListener("input", activeSaveButton);
  document.getElementById("email").addEventListener("input", activeSaveButton);
  document
    .getElementById("form-name")
    .addEventListener("submit", handleFormNameSubmit);
  document
    .getElementById("form-password")
    .addEventListener("submit", handleFormPasswordSubmit);
  document
    .getElementById("edit-name-icon")
    .addEventListener("click", () => ToggleFormNameCard("open"));
  document
    .getElementById("close-name-form")
    .addEventListener("click", () => ToggleFormNameCard("close"));
  document
    .getElementById("edit-password-icon")
    .addEventListener("click", () => ToggleFormPasswordCard("open"));
  document
    .getElementById("close-password-form")
    .addEventListener("click", () => ToggleFormPasswordCard("close"));
});

async function handleFormNameSubmit(event) {
  event.preventDefault();
  calert.hide();

  const json = jsonFromForm(event.target);
  try {
    const response = await post("/api/profile/edit", json);
    console.log(response);
    calert.success("User updated successfully");
  } catch (error) {
    calert.error("Error updating user data : " + error.message);
    console.error("Error updating user", error);
  }
}

async function handleFormPasswordSubmit(event) {
  event.preventDefault();
  const form = event.target;
  const json = jsonFromForm(form);
  const password = json.newPassword;
  const password2 = json.confirmPassword;
  if (password !== password2) {
    calert.error("Passwords do not match");
    return;
  }
  try {
    await post("/api/profile/change-password", json);
    calert.success("Password updated successfully");
  } catch (error) {
    calert.error("Error updating password : " + error.message);
    console.error("Error updating password", error);
  }
}

function navTo(path) {
  window.location.href = path;
}

function handleFormsDisplay(formId, action) {
  let formButton = document.getElementById("forms-trigger");
  let form = document.getElementById(formId);

  if (action === "open") {
    formButton.style.display = "none";
    form.style.display = "block";
  } else {
    formButton.style.display = "block";
    form.style.display = "none";
  }
}

function ToggleFormNameCard(action = "open") {
  handleFormsDisplay("form-name-card", action);
}

function ToggleFormPasswordCard(action = "open") {
  handleFormsDisplay("form-password-card", action);
}

function activeSaveButton() {
  const oldName = "${user.name}";
  const newName = document.getElementById("name").value;
  const oldEmail = "${user.email}";
  const newEmail = document.getElementById("email").value;
  var button = document.getElementById("save-name-button");
  if (oldName === newName && oldEmail === newEmail) {
    button.disabled = true;
  } else {
    button.disabled = false;
  }
}
