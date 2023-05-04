function openModal() {
    var modal = document.getElementById("modal");
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById("modal");
    modal.style.display = "none";
}

/* Event listener */
var addTaskButton = document.getElementById("add-task-button");
addTaskButton.addEventListener("click", openModal);

var closeButton = document.getElementById("close-button");
closeButton.addEventListener("click", closeModal);

/* AJAX request */
function addTask() {
    var title = document.getElementById("title").value;
    var description = document.getElementById("description").value;
    var column = document.getElementById("column").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/tasks", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            var task = document.createElement("div");
            task.classList.add("task");
            task.innerHTML = "<h3>" + response.title + "</h3><span>" + response.description + "</span>";
            document.getElementById(column).appendChild(task);
            closeModal();
        }
    };
    xhr.send("title=" + title + "&description=" + description + "&column=" + column);
}

var form = document.getElementById("add-task-form");
form.addEventListener("submit", function(event) {
    event.preventDefault();
    addTask();
});