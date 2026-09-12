package com.nikhil.apigateway.filter;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.nikhil.apigateway.service.JwtService;

import reactor.core.publisher.Mono;


@Component
public class JwtAuthenticationFilter  implements GlobalFilter{
	
	private final JwtService jwtService;
	
	public JwtAuthenticationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		       String path  = exchange.getRequest().getURI().getPath();
		       
		            if(path.startsWith("/auth/")) {
		            	return chain.filter(exchange);
		            }
		
		            
		         String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		         
		                if(authHeader == null || !authHeader.startsWith("Bearer ")) {
		                	
		                	           exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		                	           
		                	           return exchange.getResponse().setComplete();
		                }
		                
		             String token = authHeader.substring(7);
		             
		             if(!jwtService.isTokenvalid(token)) {
		            	 
		            	 exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		            	 
		            	 return exchange.getResponse().setComplete();
		             }
		             
		             if(path.startsWith("/policies") && exchange.getRequest().getMethod().name().equals("DELETE")) {
		            	 
		            	String role =  jwtService.extractRole(token);
		            	
		            	System.out.println("JWT ROLE = " +role);
		            	
		            	if(!"ADMIN" .equals(role)) {
		            		exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
		            		
		            		return exchange.getResponse().setComplete();
		            	}
		             }
		
		                             return chain.filter(exchange);
		                             
	}

}
