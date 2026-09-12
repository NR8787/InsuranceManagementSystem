package com.nikhil.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nikhil.userservice.dto.UserRequestDto;
import com.nikhil.userservice.dto.UserResponseDto;
import com.nikhil.userservice.dto.UserUpdateRequestDto;

public interface UserService {
	
	public UserResponseDto createUser (UserRequestDto userRequestDto);
	
	public UserResponseDto getUserById(Long id);
	
	
	//List<UserResponseDto> getAllUsers();
	public  Page<UserResponseDto> getAllUsers(Pageable pageable);
	
    public UserResponseDto updateUser(Long id,UserUpdateRequestDto userUpdateRequestDto);
    
    void deleteUser (Long id);
}
