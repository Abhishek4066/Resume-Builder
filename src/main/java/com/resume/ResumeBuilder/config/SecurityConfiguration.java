package com.resume.ResumeBuilder.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/edit").authenticated()
                        .requestMatchers("/*").permitAll())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/access-denied"));

        return httpSecurity.build();
    }

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
		        .username("user")
		        .password(passwordEncoder().encode("user"))
		        .roles("USER")
		        .build();

		UserDetails admin = User.builder()
		        .username("admin")
		        .password(passwordEncoder().encode("admin"))
		        .roles("ADMIN")
		        .build();


		return new InMemoryUserDetailsManager(user, admin);
	}
}
