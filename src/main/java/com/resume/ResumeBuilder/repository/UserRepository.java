
package com.resume.ResumeBuilder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resume.ResumeBuilder.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
	
	
	default void logQueries() {
        findAll().forEach(user -> System.out.println(user.getUserName()));
    }
}
