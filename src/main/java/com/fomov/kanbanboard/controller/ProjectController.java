package com.fomov.kanbanboard.controller;

import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.model.Task;
import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.repository.UserRepository;
import com.fomov.kanbanboard.service.ProjectService;
import com.fomov.kanbanboard.service.TaskStatusService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final TaskStatusService taskStatusService;

    public ProjectController(ProjectService projectService, UserRepository userRepository, TaskStatusService taskStatusService) {
        this.projectService = projectService;
        this.taskStatusService = taskStatusService;
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute("project") Project project, @RequestParam("users") List<Long> userIds, Model model) {
        if (!projectService.createProject(project, userIds)) {
            model.addAttribute("errorMessage", "Project with name " + project.getName() + " already exists");
            return "homePage";
        }

        return "redirect:/homePage";
    }

    @GetMapping("/open/{id}")
    public String openProject(@PathVariable("id") Integer id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("userRole", user.getRoles().toString());
        model.addAttribute("task", new Task());
        model.addAttribute("project", projectService.getProjectById(id));
        model.addAttribute("taskStatuses", taskStatusService.getAllStatuses());
        model.addAttribute("tasksByStatus", projectService.getTasksByStatus(id));

        return "kanbanBoard";
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Integer projectId, Model model) {
        if (!projectService.deleteProject(projectId)) {
            model.addAttribute("errorMessage", "Error");
            return "kanbanBoard";
        }

        return "redirect:/homePage";
    }
}
