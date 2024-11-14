package com.example.demo.controller;

import com.example.demo.service.PasswordQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private PasswordQualityService passwordQualityService;

    // Redirect root URL ("/") to "/login"
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login"; // Redirects root URL to /login
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Renders login.html
    }

    @PostMapping("/login")
    public String login(String password, Model model) {
        if (passwordQualityService.isValidPassword(password)) {
            return "redirect:/quiz"; // Redirect to quiz page if password is valid
        } else {
            return "redirect:/badpassword"; // Redirect to bad-password page if password is invalid
        }
    }
}
