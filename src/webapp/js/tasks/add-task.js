import { post } from "../common/api.js";
import alert from "../common/alert.js";

document.addEventListener("DOMContentLoaded", function () {
    console.log("add-task.js loaded");
    setupAddTaskForm();
});

function setupAddTaskForm() {
    const form = document.querySelector("#add-task-form");
    form.addEventListener("submit", (e) => { e.preventDefault(); handleAddTask(); });
    const btn = document.querySelector("#add-task-btn");
    btn.addEventListener("click", handleAddTask);
}

async function handleAddTask() {
    const name = document.getElementById("name").value;
    if (!name) {
        alert.error("Name is required", "add-form-info");
        return;
    }
    try {
        await post("/api/tasks/create", { name });
        console.log("Task added");
        alert.success("Task added", "add-form-info");
        setTimeout(() => {
            location.reload();
        }, 2000);
    } catch (error) {
        console.error("Error adding task", error);
        alert.error("Error adding task");
    }
}
