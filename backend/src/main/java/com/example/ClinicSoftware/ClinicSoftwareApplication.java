package com.example.ClinicSoftware;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ClinicSoftware.Entity.Role;
import com.example.ClinicSoftware.Service.UserService;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories

public class ClinicSoftwareApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(ClinicSoftwareApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	//Chay lan dau de tao ra cac role trong database roi comment lai
	@Bean
	CommandLineRunner run(UserService userService)
	{
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
		};
	}

}
