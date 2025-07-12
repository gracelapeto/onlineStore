package com.example.onlineStore.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeContoller {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Online Store!");
        return "home";
    }
}
