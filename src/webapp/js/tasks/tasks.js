import { post } from "../common/api.js";
import alert from "../common/alert.js";

document.addEventListener("DOMContentLoaded", function () {
  console.log("tasks.js loaded");
  setupEditTaskButtons();
  setupDeleteTaskButtons();
  setupStatusTaskButtons();
  setupTaskNameTd();
  setupTaskDescriptionTd();
});

function setupTaskNameTd() {
  const taskNameTds = document.querySelectorAll(".task-name-td");
  for (let td of taskNameTds) {
    td.addEventListener("dblclick", handleTaskNameEdit);
  }
}

function setupTaskDescriptionTd() {
  const taskDescriptionTds = document.querySelectorAll(".task-description-td");
  for (let td of taskDescriptionTds) {
    td.addEventListener("dblclick", handleTaskDescriptionEdit);
  }
}

function handleTaskNameEdit(event) {
  const td = event.target;
  const name = td.innerText;
  const input = document.createElement("input");
  input.type = "text";
  input.value = name;
  input.classList.add("form-control");
  input.addEventListener("blur", handleTaskNameUpdate);
  input.addEventListener("keypress", handleTaskNameUpdate);
  td.innerHTML = "";
  td.appendChild(input);
  input.focus();
}

function handleTaskDescriptionEdit(event) {
  const td = event.target;
  const description = td.innerText;
  const input = document.createElement("input");
  input.type = "text";
  input.value = description;
  input.classList.add("form-control");
  input.addEventListener("blur", handleTaskDescriptionUpdate);
  input.addEventListener("keypress", handleTaskDescriptionUpdate);
  td.innerHTML = "";
  td.appendChild(input);
  input.focus();
}

async function handleTaskNameUpdate(event) {
  if (event.type === "keypress" && event.key !== "Enter") {
    return;
  }
  const input = event.target;
  const td = input.parentElement;
  const id = td.dataset.id;
  const name = input.value;
  const newTask = { name };
  try {
    await post(`/api/tasks/update/${id}`, newTask);
    console.log("Task name updated");
    alert.success("Task name updated");
    td.innerHTML = name;
  } catch (error) {
    console.error("Error updating task name", error);
    alert.error("Error updating task name");
  }
}

async function handleTaskDescriptionUpdate(event) {
  if (event.type === "keypress" && event.key !== "Enter") {
    return;
  }
  const input = event.target;
  const td = input.parentElement;
  const id = td.dataset.id;
  const description = input.value;
  const newTask = { description };
  try {
    await post(`/api/tasks/update/${id}`, newTask);
    console.log("Task description updated");
    alert.success("Task description updated");
    td.innerHTML = description;
  } catch (error) {
    console.error("Error updating task description", error);
    alert.error("Error updating task description");
  }
}

function setupEditTaskButtons() {
  const editButtons = document.querySelectorAll(".edit-task");
  for (let button of editButtons) {
    button.addEventListener("click", handleTaskEdit);
  }
}

function setupDeleteTaskButtons() {
  const deleteButtons = document.querySelectorAll(".delete-task");
  for (let button of deleteButtons) {
    button.addEventListener("click", handleTaskDelete);
  }
}

function setupStatusTaskButtons() {
  const statusButtons = document.querySelectorAll(".status-task");
  for (let button of statusButtons) {
    button.addEventListener("click", handleTaskStatus);
  }
}

function handleTaskEdit(event) {
  console.log("handleTaskEdit");
  const task = event.target;
  console.log(task);
  console.log(task.dataset.id);
}

function handleTaskDelete(event) {
  const task = event.target;
  const id = task.dataset.id;
  if (confirm("Are you sure you want to delete this task?")) {
    deleteTask(id);
  }
}

async function deleteTask(id) {
  try {
    await post(`/api/tasks/delete/${id}`);
    console.log("Task deleted");
    alert.success("Task deleted");
    location.reload();
  } catch (error) {
    console.error("Error deleting task", error);
    alert.error("Error deleting task");
  }
}

async function handleTaskStatus(event) {
  const task = event.target;
  const id = task.dataset.id;
  const isChecked = task.checked;
  const newTask = { status: isChecked ? "done" : "todo" };
  try {
    await post(`/api/tasks/update/${id}`, newTask);
    console.log("Task status updated");
    alert.success("Task status updated");
  } catch (error) {
    console.error("Error updating task status", error);
    alert.error("Error updating task status");
  }
}
