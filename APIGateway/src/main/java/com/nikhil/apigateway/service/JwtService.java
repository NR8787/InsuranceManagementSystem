package com.nikhil.apigateway.service;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final SecretKey secretKey;
	
	 public JwtService(@Value("${jwt.secret}") String secret) {

	      this.secretKey  =   Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	               
	 }
	 
	 
	 public Claims extractClaims(String token) {
		 
		 return Jwts.parser()
				 
				 .verifyWith(secretKey)
				 
				 .build()
				 
				 .parseSignedClaims(token)
				 
				 .getPayload();
	 }
	 
	 public boolean isTokenvalid(String token) {
		 
		 try {
			 
			 Jwts.parser()
			              .verifyWith(secretKey)
			              .build()
			              .parseSignedClaims(token);
			 
			 return true;
			 
		 } catch (Exception e) {
			 
			 return false;
		}
	 }
	            		   
	public String extractRole(String token) {
		
		  Claims claims =  extractClaims(token);
		  
		  return claims.get("role", String.class);
		  
		  
	}
	
	public String extractUsername(String token) {
		    Claims claims =    extractClaims(token);
		    return claims.getSubject();
	}
	

}
