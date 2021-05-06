package com.example.demo;

public class ParkingSlot {
	//add alloted car
	int status;//1 if full, 0 if empty
	Car allotedCar;
	ParkingSlot(int status){
		allotedCar = null;
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public Car getAllotedCar() {
		return allotedCar;
	}

	public void setAllotedCar(Car allotedCar) {
		this.allotedCar = allotedCar;
	}
	
	
}
