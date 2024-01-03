package com.resume.ResumeBuilder.controller;

import java.time.LocalDate;
import java.util.Arrays;
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

import com.resume.ResumeBuilder.model.Experience;
import com.resume.ResumeBuilder.model.UserProfile;
import com.resume.ResumeBuilder.repository.UserProfileRepository;

@Controller
public class HomeController {
	
	@Autowired
	UserProfileRepository profileRepository;

    @GetMapping("/")
    public String home() {
    	
    	Optional<UserProfile> profile1Optional = profileRepository.findByUserName("one");
    	profile1Optional.orElseThrow(() -> new RuntimeException("Not found: "));

        
        
        UserProfile profile1 = profile1Optional.get();
    	
    	Experience exp1 = new Experience();
    	exp1.setCompany("Google");
    	exp1.setDesignation("network engineer");
    	exp1.setId(1);
    	exp1.setStartDate(LocalDate.of(2011, 02, 12));
    	//exp1.setEndDate(LocalDate.of(2012, 02, 12));
    	exp1.setCurrentJob(true);
    	
    	
    	Experience exp2 = new Experience();
    	exp2.setCompany("Amazon");
    	exp2.setDesignation("Software engineer");
    	exp2.setId(2);
    	exp2.setStartDate(LocalDate.of(2015, 02, 12));
    	exp2.setEndDate(LocalDate.of(2018, 02, 12));
    	
    	profile1.getJobExperience().clear();
    	profile1.getJobExperience().add(exp1);
    	profile1.getJobExperience().add(exp2);
    	
    	
    	
    	
    	
    	
    	profileRepository.save(profile1);
    	
        return "profile";
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
        
        System.out.println(userProfile.getJobExperience());
        
        return "profile-templates/"+ userProfile.getTheme() + "/index";
    }


}
