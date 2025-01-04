package com.springsecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.Model.User;
import com.springsecurity.Service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5174")
public class UserController {
	
	@Autowired
    private UserService userService;

    // Endpoint for user registration
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully";
    }

}
