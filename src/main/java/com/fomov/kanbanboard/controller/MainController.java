package com.fomov.kanbanboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String welcomePage() {
        return "welcomePage";
    }

    @GetMapping("/homePage")
    public String homePage() {
        return "homePage";
    }
}
