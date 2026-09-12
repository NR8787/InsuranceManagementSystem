package com.nikhil.userservice.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.userservice.dto.UserRequestDto;
import com.nikhil.userservice.dto.UserResponseDto;
import com.nikhil.userservice.dto.UserUpdateRequestDto;
import com.nikhil.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	
	@PostMapping
	public UserResponseDto createUser( @Valid @RequestBody UserRequestDto  userRequestDto) {
	return 	userService.createUser(userRequestDto);
	
	}
	
	
	@GetMapping("/{id}")
	public UserResponseDto getUserById(@PathVariable Long id) {
	return 	userService.getUserById(id);
	}
	
	@GetMapping
	public Page<UserResponseDto> getAllUsers(
			                 @PageableDefault(size = 5 ) Pageable pageable){
	     return  userService.getAllUsers(pageable);
	
}
	
	@PutMapping("/{id}")
	public UserResponseDto updateUser(@PathVariable Long id,@Valid 
			                    @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
		   return      userService.updateUser(id, userUpdateRequestDto);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteuser (@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}