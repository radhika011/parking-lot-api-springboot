package com.example.demo;
import org.springframework.context.annotation.*;
@Configuration
public class AppConfig {
	@Bean(initMethod = "init")
	ParkingLot myParkingLot() {
		return new ParkingLot();
	}
}
