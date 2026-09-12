package com.nikhil.policyservice.service.impl;

import java.util.List;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.nikhil.policyservice.clients.UserFeignClient;
import com.nikhil.policyservice.dto.PolicyRequestDto;
import com.nikhil.policyservice.dto.PolicyResponseDto;
import com.nikhil.policyservice.entity.Policy;
import com.nikhil.policyservice.exception.PolicyNotFoundException;
import com.nikhil.policyservice.exception.UserServiceUnavailableException;
import com.nikhil.policyservice.repository.PolicyRepository;
import com.nikhil.policyservice.service.PolicyService;


@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
	private final UserFeignClient userFeignClient;
	
	private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;
	
	public PolicyServiceImpl(PolicyRepository policyRepository,
			                   UserFeignClient userFeignClient,
			                   CircuitBreakerFactory<?,?> circuitBreakerFactory) {
		
	
			                          
		this.policyRepository = policyRepository;
		this.userFeignClient = userFeignClient;
		this.circuitBreakerFactory = circuitBreakerFactory;
	}
    
	
	
	@Override
	public PolicyResponseDto createPolicy(PolicyRequestDto policyRequestDto) {
		
		circuitBreakerFactory.create("userService").run(() -> 
		
		
		 // Check whether user exists
	          	userFeignClient.getUserById(policyRequestDto.getUserId()),
	          	throwable -> {
	          		throw new UserServiceUnavailableException("User-Service currently unavailable");
	          	}
	          	
	          	);
		
		Policy policy = new Policy();
		
		policy.setUserId(policyRequestDto.getUserId());
		
		policy.setPolicyNumber(policyRequestDto.getPolicyNumber());
		
		policy.setPolicyType(policyRequestDto.getPolicyType());
		
		policy.setPremium(policyRequestDto.getPremium());
		
		policy.setCoverageAmount(policyRequestDto.getCoverageAmount());
		
		
		
		Policy savePolicy = policyRepository.save(policy);
		
		
		
		PolicyResponseDto responseDto = new PolicyResponseDto();
		
		
		
		responseDto.setId(savePolicy.getId());
		
		responseDto.setUserId(savePolicy.getUserId());
		
		responseDto.setPolicyNumber(savePolicy.getPolicyNumber());
		
		responseDto.setPolicyType(savePolicy.getPolicyType());
		
		responseDto.setPremium(savePolicy.getPremium());
		
		responseDto.setCoverageAmount(savePolicy.getCoverageAmount());
		
		
		
		
		   return responseDto;
		     
		   
		
	}

	@Override
	public PolicyResponseDto getPolicyById(Long id) {
		
              Policy policy =  policyRepository.findById(id).orElseThrow(() ->
                                                   new PolicyNotFoundException("Policy not found with id :" + id));
              
              PolicyResponseDto responseDto = new PolicyResponseDto();
              
              responseDto.setId(policy.getId());
              responseDto.setUserId(policy.getUserId());
              responseDto.setPolicyNumber(policy.getPolicyNumber());
              responseDto.setPolicyType(policy.getPolicyType());
              responseDto.setPremium(policy.getPremium());
              responseDto.setCoverageAmount(policy.getCoverageAmount());
              
            return   responseDto;
	}

	@Override
	public List<PolicyResponseDto> getAllPolicies() {
	
		        List<Policy> policies =  policyRepository.findAll();
		        
		        return policies.stream().map(policy -> {
		        	
		        	 PolicyResponseDto responseDto = new PolicyResponseDto();

		                responseDto.setId(policy.getId());
		                responseDto.setUserId(policy.getUserId());
		                responseDto.setPolicyNumber(policy.getPolicyNumber());
		                responseDto.setPolicyType(policy.getPolicyType());
		                responseDto.setPremium(policy.getPremium());
		                responseDto.setCoverageAmount(policy.getCoverageAmount());

		                return responseDto;
		        })
		        		.toList();
	}

	@Override
	public PolicyResponseDto  updatePolicy(Long id, PolicyRequestDto policyRequestDto) {
		
		     Policy existingPolicy =  policyRepository.findById(id).orElseThrow(()-> 
		                                                  new PolicyNotFoundException("Policy not found with id : " + id));
		     existingPolicy.setUserId(policyRequestDto.getUserId());
		     
		     existingPolicy.setPolicyNumber(policyRequestDto.getPolicyNumber());
		     
		     existingPolicy.setPolicyType(policyRequestDto.getPolicyType());
		     
		     existingPolicy.setPremium(policyRequestDto.getPremium());
		     
		     existingPolicy.setCoverageAmount(policyRequestDto.getCoverageAmount());
		     
		     Policy updatedPolicy = policyRepository.save(existingPolicy);
		     
		     PolicyResponseDto responseDto = new PolicyResponseDto();

		     responseDto.setId(updatedPolicy.getId());
		     
		     responseDto.setUserId(updatedPolicy.getUserId());
		     
		     responseDto.setPolicyNumber(updatedPolicy.getPolicyNumber());
		     
		     responseDto.setPolicyType(updatedPolicy.getPolicyType());
		     
		     responseDto.setPremium(updatedPolicy.getPremium());
		     
		     responseDto.setCoverageAmount(updatedPolicy.getCoverageAmount());

		     return responseDto;
	}

	@Override
	public void deletePolicy(Long id) {
		
	Policy deletepolicy = policyRepository.findById(id).orElseThrow(() ->
		                                      new PolicyNotFoundException("Policy not found with id : " + id));
	    
		         policyRepository.delete(deletepolicy);
		
	}

}
