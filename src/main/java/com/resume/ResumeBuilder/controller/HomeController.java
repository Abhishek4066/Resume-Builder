package com.resume.ResumeBuilder.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.ResumeBuilder.model.Education;
import com.resume.ResumeBuilder.model.Experience;
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

// make a post reques to add eduation so that it gets saved
	@GetMapping("/edit")
	public String edit(Model model, Principal principal, @RequestParam(required = false) String add) {

		String userName = principal.getName();

		model.addAttribute("userName", userName);

		Optional<UserProfile> userProfileOptional = profileRepository.findByUserName(userName);

		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));

		UserProfile userProfile = userProfileOptional.get();

		if ("experience".equals(add)) {
			userProfile.getJobExperience().add(new Experience());

		} else if ("education".equals(add)) {
			userProfile.getEducations().add(new Education());

		} else if ("skill".equals(add)) {
			userProfile.getSkills().add("");
		}

		model.addAttribute("userProfile", userProfile);

		return "profile-edit";
	}

	@PostMapping("/edit")
	public String postEdit(Model model, Principal principal, @ModelAttribute UserProfile userProfile) {
		String userName = principal.getName();
		Optional<UserProfile> userProfileOptional = profileRepository.findByUserName(userName);
		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));
		UserProfile savedUserProfile = userProfileOptional.get();
		userProfile.setId(savedUserProfile.getId());
		userProfile.setUserName(userName);
		profileRepository.save(userProfile);
		return "redirect:/view/" + userName;
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

	@GetMapping("/view/{userName}") // change userId to username
	public String view(@PathVariable String userName, Model model, Principal principal) {
		// Do something with userId, e.g., fetch user details from the database
		// and add them to the model

		if (principal != null && principal.getName() != "") {
			boolean currentUsersProfile = principal.getName().equals(userName);
			model.addAttribute("currentUsersProfile", currentUsersProfile);
		}
		// String userName = principal.getName();

		Optional<UserProfile> userProfileOptional = profileRepository.findByUserName(userName);

		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));

		model.addAttribute("userName", userName);

		UserProfile userProfile = userProfileOptional.get();

		model.addAttribute("userProfile", userProfile);

		System.out.println(userProfile.getJobExperience());
		System.out.println(userProfile.getEducations());

		return "profile-templates/" + userProfile.getTheme() + "/index";
	}

	@GetMapping("/delete")
	public String delete(Model model, Principal principal, @RequestParam String type, @RequestParam int index) {
		String userName = principal.getName();
		Optional<UserProfile> userProfileOptional = profileRepository.findByUserName(userName);
		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));
		UserProfile userProfile = userProfileOptional.get();
		if ("experience".equals(type)) {
			userProfile.getJobExperience().remove(index);
		} else if ("education".equals(type)) {
			userProfile.getEducations().remove(index);
		} else if ("skill".equals(type)) {
			userProfile.getSkills().remove(index);
		}
		profileRepository.save(userProfile);
		return "redirect:/edit";
	}
}
