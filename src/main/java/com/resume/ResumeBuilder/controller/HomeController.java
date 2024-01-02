package com.resume.ResumeBuilder.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit() {
        return "Edit";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "Access Denied - You are not authorized to access this page.";
    }

    // Exception handler for AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "access-denied";
    }
    
    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model) {
        // Do something with userId, e.g., fetch user details from the database
        // and add them to the model
        model.addAttribute("userId", userId);
        
        return "profile";
    }

}