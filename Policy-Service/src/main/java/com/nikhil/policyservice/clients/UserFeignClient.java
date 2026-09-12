package com.nikhil.policyservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nikhil.policyservice.dto.UserResponseDto;

@FeignClient(name = "User-Service")
public interface UserFeignClient {
	
	@GetMapping("/users/{id}")
	
	UserResponseDto getUserById(@PathVariable Long id);
	
	

}
