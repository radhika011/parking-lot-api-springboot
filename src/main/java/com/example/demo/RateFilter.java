package com.example.demo;
import com.example.demo.RateLimiter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Component;
import java.util.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
@Component
public  class RateFilter implements Filter{
	
	HashMap <String,RateLimiter> dictionary;
	@Override
	   public void destroy() {}

	   
	   @ExceptionHandler
	   
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) 
	      throws IOException, ServletException {
		   //System.out.println("Do filter invoked!");
		   HttpServletRequest httpsrequest = (HttpServletRequest) request;
	       HttpServletResponse httpsresponse = (HttpServletResponse) response;
		   RateLimiter limiter;
		   String current_ip = request.getRemoteAddr();   //gets ip address of the client
		   if(dictionary.containsKey(current_ip)) {        //if it is not a new client
			   limiter= dictionary.get(current_ip);      //get the limiter object assigned to that ip address
		   }
		   else {                                             //if it is a new client
			   limiter = new RateLimiter();             //create a new limiter object
			   dictionary.put(current_ip, limiter);      //store it in the dictionary
		   }
	      //System.out.println("Remote Host:"+request.getRemoteHost());
	      //System.out.println("Remote Address:"+request.getRemoteAddr());
	      //System.out.println(limiter.availableTokens);
	      if(limiter.tryConsume()==true) {                    //if tokens are available to be consumed, allow the request to pass through
	    	  filterchain.doFilter(request, response);         
	      }
	      else {                                                    
	    	  httpsresponse.sendError(HttpStatus.TOO_MANY_REQUESTS.value());  //else, respond with a 429 error
	      }
	      //System.out.println("Do filter done!");
		   
	   }
	   @Override
	   public void init(FilterConfig filterconfig) throws ServletException {
		   //System.out.println("Init invoked!");
		   dictionary = new HashMap<String,RateLimiter>();         //creates a dictionary object when filter is placed into service
	   }


	
	
}
