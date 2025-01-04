package com.springsecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springsecurity.Service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Autowired
	private MyUserDetailsService myuserDetailsService;
	
	
	//Configuration
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(request -> request
	            .requestMatchers("/", "/login", "/api/register").permitAll() 
	            .requestMatchers("/user/**").hasAuthority("ROLE_USER")	            
	            .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")	
	            .anyRequest().authenticated()
	        )
		.formLogin(customizer -> customizer
			.defaultSuccessUrl("/", true) 
			.permitAll()
	);
		
		return http.build();
	}
	
	
	//Custom Authorization Bean
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(myuserDetailsService);
		return daoAuthenticationProvider;
	}
	
	// BCrypt Password Encoder Bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

}
