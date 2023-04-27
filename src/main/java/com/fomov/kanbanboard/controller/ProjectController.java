package com.fomov.kanbanboard.controller;

import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.repository.UserRepository;
import com.fomov.kanbanboard.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService, UserRepository userRepository) {
        this.projectService = projectService;
    }

    @PostMapping("/createProject")
    public String createProject(@ModelAttribute("project") Project project, @RequestParam("users") List<Long> userIds, Model model) {
        if (!projectService.createProject(project, userIds)) {
            model.addAttribute("errorMessage", "Project with name " + project.getName() + " already exists");
            return "homePage";
        }

        return "redirect:/homePage";
    }
}
