package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import java.io.*;
import org.springframework.stereotype.Component;
@RestController

public class PrincipalController {
	@Autowired
	ParkingLot parkingLot;
	@RequestMapping()
	String sayHello() {
		return "Hello World!";
	}
	@RequestMapping(value="/parkingLot/car/carId/{carId}/park")
	String park(@PathVariable int carId,HttpServletResponse response) throws IOException{
		Car car = new Car(carId);
		int slotNo = car.park(parkingLot);
		if(slotNo==-1) {
			response.sendError(HttpStatus.BAD_REQUEST.value());
		}
		StringBuilder message = new StringBuilder();
		message.append("Car ").append(carId).append(" parked at ").append(slotNo);
		return  message.toString();
	}
	@RequestMapping(value="/parkingLot/slot/slotNo/{slotNo}/unpark")
	String unpark(@PathVariable int slotNo,HttpServletResponse response) throws IOException{
		Car unparked = parkingLot.unPark(slotNo);
		if(unparked==null) {
			response.sendError(HttpStatus.BAD_REQUEST.value());
		}
		StringBuilder message = new StringBuilder();
		message.append("Car with car ID ").append(unparked.getCarID()).append(" unparked from ").append(slotNo);
		return message.toString();
	}
	@RequestMapping(value="/parkingLot/slot/slotNo/{slotNo}",method = RequestMethod.GET)
	String showCarID(@PathVariable int slotNo) {
		int carId = parkingLot.getBySlot(slotNo);
		StringBuilder message = new StringBuilder();
		message.append("Car with ID: ").append(carId).append(" at slot number: ").append(slotNo);
		return message.toString();
	}
	@RequestMapping(value="/parkingLot/car/carId/{carId}",method = RequestMethod.GET)
	String showSlotNo(@PathVariable int carId) {
		StringBuilder message = new StringBuilder();
		message.append("Car with ID: ").append(carId).append(" at slot number: ").append(parkingLot.getByCarID(carId));
		return message.toString();
	}
}
