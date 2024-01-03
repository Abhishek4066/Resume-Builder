package com.resume.ResumeBuilder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resume.ResumeBuilder.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserName(String userName);
}
