package com.demo.auth.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.auth.entity.User;
import com.demo.auth.repository.UserRepository;
import com.demo.auth.security.JwtService;
import com.demo.auth.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private JwtService jwtService;
	
	private final  UserRepository userRepository;
	
	public UserServiceImpl (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User registerUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public String verifyLogin(String username, String password) {
		
		Optional<User> user  = userRepository.findByUsername(username);
		
		if(user.isPresent() && user.get().getPassword().equals(password)) {
			
			return jwtService.generateToken(username);
		 
			//	return "Login Success";
			} else {
				throw new RuntimeException("Invalid username or password");
		}
		
		
	}
	

	
	
	
	

	
	
}
