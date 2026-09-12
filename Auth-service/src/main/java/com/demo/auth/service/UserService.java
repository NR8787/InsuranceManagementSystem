package com.demo.auth.service;

import com.demo.auth.entity.User;

public interface UserService {
	
		
		User registerUser(User user);
		
		String verifyLogin(String username,String password);

	}


