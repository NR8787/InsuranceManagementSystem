package com.demo.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.auth.entity.User;
import com.demo.auth.service.UserService;
import com.demo.dto.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private  final UserService userService;
	
	public AuthController (UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		User saveuser = userService.registerUser(user);
		return ResponseEntity.ok(saveuser);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		            String result =  userService.verifyLogin(
                                     loginRequest.getUsername(),
                                     loginRequest.getPassword());
		                      
		             return ResponseEntity.ok(result);
	}

}
