package com.example.demo;
import java.time.*;

public class RateLimiter {
	long availableTokens;
	long lastRefillTime;
	static long capacity;
	static int refillRate = 10000;
	RateLimiter(){
		availableTokens =10;//1;
		lastRefillTime = System.currentTimeMillis();//returns current time in ms
		capacity =10;//1;
	
	}
	boolean tryConsume() {
		refill();
		if(availableTokens==0) {
			return false;
		}
		else {
			availableTokens--;
		}
		return true;
	}
	void refill() {
		long now = System.currentTimeMillis();
		long elapsedTime = now-lastRefillTime;
		
			long tokensToBeAdded = (elapsedTime/refillRate)*capacity;
			//System.out.println(tokensToBeAdded);
			availableTokens = Math.min(capacity, availableTokens+tokensToBeAdded);
			lastRefillTime = now;
		
		
		
	}
}