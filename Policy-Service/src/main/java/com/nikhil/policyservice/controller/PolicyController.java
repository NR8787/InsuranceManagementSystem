package com.nikhil.policyservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.policyservice.dto.PolicyRequestDto;
import com.nikhil.policyservice.dto.PolicyResponseDto;
import com.nikhil.policyservice.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/policies")
public class PolicyController {
	
	private final PolicyService policyService;
	
	public PolicyController(PolicyService policyService) {
		this.policyService = policyService;
		
	}
        
	    @PostMapping
        public PolicyResponseDto createPolicy( @Valid @RequestBody PolicyRequestDto policyRequestDto) {
        	       return    policyService.createPolicy(policyRequestDto);
        }
	    
	    
	 @GetMapping("/{id}")
	public PolicyResponseDto getPolicyById( @PathVariable Long  id) {
		   return   policyService.getPolicyById(id);
		
	}
	 
	 
	@GetMapping
	public List<PolicyResponseDto> getAllPolicies(){
		  return   policyService.getAllPolicies();
	}
	
	
	@PutMapping("/{id}")
	public PolicyResponseDto updatePolicy(@PathVariable Long id, @Valid @RequestBody PolicyRequestDto policyRequestDto) {
		             return    policyService.updatePolicy(id, policyRequestDto);
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePolicy(@PathVariable Long  id){
		         policyService.deletePolicy(id);
		         return ResponseEntity.noContent().build();
		
	}

	
	
}
