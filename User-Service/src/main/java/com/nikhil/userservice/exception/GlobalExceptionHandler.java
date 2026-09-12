package com.nikhil.userservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String,Object>>handleUserNotFound(UserNotFoundException ex) {
		 Map<String,Object> error =  Map.of(
		                		                 "status",404,
		                		              "message",ex.getMessage());
		                
		                return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		                
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> handleValidationException(
			MethodArgumentNotValidException ex) {
		
		Map<String,String> errors = new HashMap();
		
	                    	ex.getBindingResult()
		                   .getFieldErrors()
		                  .forEach(error -> {
			errors.put(error.getField(),error.getDefaultMessage());
		});
	                    	
	                    	Map<String,Object> response = new HashMap<>();
	                    	
	                    	response.put("status", 400);
	                    	response.put("message", "Validation failed");
	                    	response.put("errors", errors);
	                    	
	                    	return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<Map<String,Object>> handleDuplicateEmail(DuplicateEmailException ex) {
		                                
		        Map<String,Object>  error =     Map.of(
		                                           "status",409,
		          
		                                           "message",ex.getMessage());
		                                           return new ResponseEntity<>(error,HttpStatus.CONFLICT);
	}
}
