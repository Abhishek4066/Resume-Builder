package com.resume.ResumeBuilder.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.resume.ResumeBuilder.model.UserProfile;
import com.resume.ResumeBuilder.repository.UserProfileRepository;

@Controller
public class HomeController {
	
	@Autowired
	UserProfileRepository profileRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/edit")
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
    
    @GetMapping("/view/{userName}") //change userId to username
    public String view(@PathVariable String userName, Model model) {
        // Do something with userId, e.g., fetch user details from the database
        // and add them to the model
        
        Optional<UserProfile> userProfileOptional = profileRepository.findByUserName(userName);
        
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));

        model.addAttribute("userId", userName);
        
        UserProfile userProfile = userProfileOptional.get();
        
        model.addAttribute("userProfile", userProfile);
        
        return "profile-templates/"+ userProfile.getTheme() + "/index";
    }


}
