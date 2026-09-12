package com.nikhil.policyservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.micrometer.MicrometerObservationCapability;
import io.micrometer.observation.ObservationRegistry;

@Configuration
public class FeignConfig {
	
	@Bean
	public MicrometerObservationCapability micrometerObservationCapability(
			
			ObservationRegistry observationRegistry) {
		
		return new MicrometerObservationCapability(observationRegistry);
	}
	
	

}
