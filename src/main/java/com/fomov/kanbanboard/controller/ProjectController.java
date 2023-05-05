package com.fomov.kanbanboard.controller;

import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.model.Task;
import com.fomov.kanbanboard.repository.UserRepository;
import com.fomov.kanbanboard.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService, UserRepository userRepository) {
        this.projectService = projectService;
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
        model.addAttribute("task", new Task());
        model.addAttribute("project", projectService.getProjectById(id));
        return "kanbanBoard";
    }
}
