package com.springsecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String Home(HttpServletRequest request) {
		String ID= request.getSession().getId();
		return "Hello User " + ID;
	}
	
	@GetMapping("/user/dashboard")
	public String UserDashboard(HttpServletRequest request) {
		return "Welcome to User Dashboard";
	}
	
	@GetMapping("/admin/dashboard")
	public String AdminDashboard(HttpServletRequest request) {
		return "Welcome to Admin Dashboard";
	}

}
