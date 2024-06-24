import { post } from "../common/api.js";
import alert from "../common/alert.js";

document.addEventListener("DOMContentLoaded", function () {
  console.log("tasks.js loaded");
  setupEditTaskButtons();
  setupDeleteTaskButtons();
  setupStatusTaskButtons();
});

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
