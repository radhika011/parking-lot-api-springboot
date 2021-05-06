package com.example.demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.*;
import javax.servlet.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.*;
import org.springframework.stereotype.Component;
@RestController

public class PrincipalController {
	ParkingLot pl1 = new ParkingLot();
	@RequestMapping()
	String sayHello() {
		return "Hello World!";
	}
	@RequestMapping(value="/park/id")
	String park(@RequestParam("id") int carID,HttpServletResponse response) throws IOException{
		Car car1 = new Car(carID);
		int slotNo = car1.park(pl1);
		if(slotNo==-1) {
			response.sendError(HttpStatus.BAD_REQUEST.value());
		}
		return  "Car "+carID+" parked at "+slotNo;
	}
	@RequestMapping(value="/unpark/slot")
	String unpark(@RequestParam("slot") int slotNo,HttpServletResponse response) throws IOException{
		Car unparked = pl1.unPark(slotNo);
		if(unparked==null) {
			response.sendError(HttpStatus.BAD_REQUEST.value());
		}
		return "Car with car ID "+unparked.getCarID()+" unparked from "+slotNo;
	}
	@RequestMapping(value="/getBySlot/slot",method = RequestMethod.GET)
	String showCarID(@RequestParam("slot") int slotNo) {
		int carID = pl1.getBySlot(slotNo);
		return "Car with ID: "+carID+" at slot number:"+slotNo;
	}
	@RequestMapping(value="/getByCarID/carID",method = RequestMethod.GET)
	String showSlotNo(@RequestParam("carID") int carID) {
		return "Car with ID: "+carID+" at slot number:"+pl1.getByCarID(carID);
	}
}
