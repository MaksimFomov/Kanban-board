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