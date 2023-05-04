const createTaskModal = document.getElementById("create-task-modal");
const createTaskBtn = document.querySelector(".create-task-btn");
const modalCloseBtn = document.querySelector(".modal-close-btn");
const createTaskSubmitBtn = document.getElementById("create-task-submit-btn");
const taskLists = document.querySelectorAll(".task-list");

// Add event listener to create task button to show the modal
createTaskBtn.addEventListener("click", () => {
    createTaskModal.style.display = "block";
});

// Add event listener to close button to hide the modal
modalCloseBtn.addEventListener("click", () => {
    createTaskModal.style.display = "none";
});

// Add event listener to submit button to create task and add it to the "To Do" column
createTaskSubmitBtn.addEventListener("click", (e) => {
    e.preventDefault();
    const name = document.getElementById("name").value;
    const description = document.getElementById("description").value;
    const estimate = document.getElementById("estimate").value;
    const priority = document.getElementById("priority").value;
    const sprint = document.getElementById("sprint").value;
    const task = createTask(name, description, estimate, priority, sprint);
    taskLists[0].appendChild(task);
    createTaskModal.style.display = "none";
});

// Create a new task element
function createTask(name, description, estimate, priority, sprint) {
    const task = document.createElement("div");
    task.classList.add("task");
    task.setAttribute("draggable", "true");
    task.innerHTML = '<h3>${name}</h3> <p>${description}</p> <ul> <li>Estimate: ${estimate}</li> <li>Priority: ${priority}</li> <li>Sprint: ${sprint}</li> </ul>';
    task.addEventListener("dragstart", dragStart);
    task.addEventListener("dragend", dragEnd);
    return task;
}

// Drag and drop functionality
let draggedTask = null;

function dragStart() {
    draggedTask = this;
    setTimeout(() => {
        this.style.opacity = "0.5";
    }, 0);
}

function dragEnd() {
    draggedTask = null;
    this.style.opacity = "1";
}

for (const taskList of taskLists) {
    taskList.addEventListener("dragover", (e) => {
        e.preventDefault();
    });

    taskList.addEventListener("drop", (e) => {
        e.preventDefault();
        taskList.appendChild(draggedTask);
    });
}