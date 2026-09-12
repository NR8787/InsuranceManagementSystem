package com.nikhil.policyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PolicyNotFoundException.class)
	 public ResponseEntity<String> handlePolicyNotFoundException(PolicyNotFoundException ex) {
		 
		      return   ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	 }

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(
			       MethodArgumentNotValidException ex) {
		
		        String message =  ex.getBindingResult()
		                          .getFieldError()
		                          .getDefaultMessage();
		        
		        return ResponseEntity
		        		          .status(HttpStatus.BAD_REQUEST)
		        		  
		        		          .body(message);
	}
	
	@ExceptionHandler(FeignException.NotFound.class)
	public ResponseEntity<String> handleFeignNotFoundException(
			FeignException.NotFound ex) {
		
		return ResponseEntity
				          .status(HttpStatus.NOT_FOUND)
				          .body(ex.contentUTF8());	
	}
	
	
	@ExceptionHandler(UserServiceUnavailableException.class)
	public ResponseEntity<String> handleUserrServiceUnavailable(UserServiceUnavailableException ex) {
		                    
		                   return ResponseEntity
		                		         .status(HttpStatus.SERVICE_UNAVAILABLE)
		                		         .body(ex.getMessage());
	}
}
