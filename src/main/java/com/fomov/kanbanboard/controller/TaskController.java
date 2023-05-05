package com.fomov.kanbanboard.controller;

import com.fomov.kanbanboard.model.Task;
import com.fomov.kanbanboard.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public String createTask(@PathVariable Integer projectId, @ModelAttribute("task") Task task, Model model) {
        if (!taskService.createTask(task, projectId)) {
            model.addAttribute("errorMessage", "Project with name " + task.getName() + " already exists");
            return "kanbanBoard";
        }

        return "redirect:/projects/open/" + projectId;
    }
}
