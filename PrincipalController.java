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
	ParkingLot pl1;
	@RequestMapping()
	String sayHello() {
		return "Hello World!";
	}
	@RequestMapping(value="/parking-lot/car/car-id/{car_id}/park")
	String park(@PathVariable int car_id,HttpServletResponse response) throws IOException{
		Car car1 = new Car(car_id);
		int slotNo = car1.park(pl1);
		if(slotNo==-1) {
			response.sendError(HttpStatus.BAD_REQUEST.value());
		}
		return  "Car "+car_id+" parked at "+slotNo;
	}
	@RequestMapping(value="/parking-lot/slot/slot-no/{slot_no}/unpark")
	String unpark(@PathVariable int slot_no,HttpServletResponse response) throws IOException{
		Car unparked = pl1.unPark(slot_no);
		if(unparked==null) {
			response.sendError(HttpStatus.BAD_REQUEST.value());
		}
		return "Car with car ID "+unparked.getCarID()+" unparked from "+slot_no;
	}
	@RequestMapping(value="/parking-lot/slot/slot-no/{slot_no}",method = RequestMethod.GET)
	String showCarID(@PathVariable int slot_no) {
		int carID = pl1.getBySlot(slot_no);
		return "Car with ID: "+carID+" at slot number:"+slot_no;
	}
	@RequestMapping(value="/parking-lot/car/car-id/{car_id}",method = RequestMethod.GET)
	String showSlotNo(@PathVariable int car_id) {
		return "Car with ID: "+car_id+" at slot number:"+pl1.getByCarID(car_id);
	}
}

