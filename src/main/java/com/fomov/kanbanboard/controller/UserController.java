package com.fomov.kanbanboard.controller;

import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "User with email " + user.getEmail() + " already exists");
            return "registration";
        }

        return "redirect:/login";
    }
}
