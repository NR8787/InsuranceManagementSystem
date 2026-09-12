package com.demo.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.auth.entity.User;
import com.demo.auth.repository.UserRepository;

@Service
public class AuthService {
	

	    @Autowired
	    private UserRepository userRepository;

	    public String register(User user) {

	        if(userRepository.findByUsername(user.getUsername()) != null){
	            throw new RuntimeException("Username already exists");
	        }

	        userRepository.save(user);

	        return "User Registered Successfully";
	    }
	}
	


