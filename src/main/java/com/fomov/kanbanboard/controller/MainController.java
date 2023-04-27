package com.fomov.kanbanboard.controller;

import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcomePage() {
        return "welcomePage";
    }

    @GetMapping("/homePage")
    public String homePage(Model model) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("userRole", user.getRoles().toString());
        model.addAttribute("project", new Project());
        model.addAttribute("allUsers", userService.getAllUsers());
        return "homePage";
    }
}
