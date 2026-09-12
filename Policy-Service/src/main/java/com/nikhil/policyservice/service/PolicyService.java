package com.nikhil.policyservice.service;

import java.util.List;

import com.nikhil.policyservice.dto.PolicyRequestDto;
import com.nikhil.policyservice.dto.PolicyResponseDto;

public interface PolicyService {
	
	public PolicyResponseDto createPolicy(PolicyRequestDto policyRequestDto);
	
	public PolicyResponseDto getPolicyById(Long id);
	
	public List<PolicyResponseDto> getAllPolicies();
	
	public PolicyResponseDto updatePolicy(Long id,PolicyRequestDto policyRequestDto);
	
	void deletePolicy(Long id);

}
