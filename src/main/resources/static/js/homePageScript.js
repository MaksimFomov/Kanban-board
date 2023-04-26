// Открыть модальное окно
function openModal() {
    document.getElementById("create-project-modal").style.display = "block";
}

// Закрыть модальное окно
function closeModal() {
    document.getElementById("create-project-modal").style.display = "none";
}

// Закрыть модальное окно при клике вне его
window.onclick = function(event) {
    var modal = document.getElementById("create-project-modal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
