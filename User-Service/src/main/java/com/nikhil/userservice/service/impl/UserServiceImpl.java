package com.nikhil.userservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nikhil.userservice.dto.UserRequestDto;
import com.nikhil.userservice.dto.UserResponseDto;
import com.nikhil.userservice.dto.UserUpdateRequestDto;
import com.nikhil.userservice.entity.User;
import com.nikhil.userservice.exception.DuplicateEmailException;
import com.nikhil.userservice.exception.UserNotFoundException;
import com.nikhil.userservice.repository.UserRepository;
import com.nikhil.userservice.service.UserService;

@Service
public class UserServiceImpl  implements UserService{

	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		
		if(userRepository.existsByEmail(userRequestDto.getEmail())) {
			throw new DuplicateEmailException("Email already exists : " + userRequestDto.getEmail());
		}
		    User user = new User();
		    
		    user.setName(userRequestDto.getName());
		    user.setEmail(userRequestDto.getEmail());
		    user.setPhone(userRequestDto.getPhone());
		    
		User saveUser = userRepository.save(user);
		
		UserResponseDto response =  new UserResponseDto();
		response.setId(saveUser.getId());
		response.setName(saveUser.getName());
		response.setEmail(saveUser.getEmail());
		response.setPhone(saveUser.getPhone());
		
		return response;
	}

	@Override
	public UserResponseDto getUserById(Long id) {
		
		    User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));
		
	                UserResponseDto response  =  new UserResponseDto();
	                response.setId(user.getId());
	                response.setName(user.getName());
	                response.setEmail(user.getEmail());
	                response.setPhone(user.getPhone());
	                
	                return response;
	                
	                
	}

	@Override
	//public List<UserResponseDto> getAllUsers() {
		public Page<UserResponseDto> getAllUsers( Pageable pageable) {
		
		return userRepository.findAll(pageable).map(user -> {
			
			     UserResponseDto response = new UserResponseDto();
			     
			      response.setId(user.getId());
			     
		          response.setName(user.getName());
		          
		          response.setEmail(user.getEmail());
		          
		          response.setPhone(user.getPhone());
		         
		          return response;
		});
		          
		
           
	}

	@Override
	public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
		
		User user =  userRepository.findById(id).orElseThrow(() -> new 
				
		    		                UserNotFoundException("User not found with id : " + id));
		
		if(userRepository.existsByEmailAndIdNot(userUpdateRequestDto.getEmail(),id)) {
			throw new DuplicateEmailException("Email already exists : " + userUpdateRequestDto.getEmail());
		}
		
		user.setName(userUpdateRequestDto.getName());
		user.setEmail(userUpdateRequestDto.getEmail());
		user.setPhone(userUpdateRequestDto.getPhone());
		
		User updateUser = userRepository.save(user);
		
		UserResponseDto response = new UserResponseDto();
		
		response.setId(updateUser.getId());
		response.setName(updateUser.getName());
		response.setEmail(updateUser.getEmail());
		response.setPhone(updateUser.getPhone());
		
		return response;
	}

	@Override
	public void deleteUser(Long id) {
	           User user =	  userRepository.findById(id).orElseThrow(() ->
		  
		                  new UserNotFoundException("User not found with id :  " + id));
		  
		  userRepository.delete(user);
		
	}
	
	


}
