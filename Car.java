package com.example.demo;

public class Car {
	int carID;
	ParkingSlot allotedSlot;
	
	public ParkingSlot getAllotedSlot() {
		return allotedSlot;
	}
	public void setAllotedSlot(ParkingSlot allotedSlot) {
		this.allotedSlot = allotedSlot;
	}
	Car(int carID){
		this.carID = carID;
		allotedSlot = null;
	}
	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID = carID;
	}
	int park(ParkingLot pl1) {                 //park method - Parameter: Parking Lot object, Returns SlotNo where the cark is parked
		
		if(pl1.getStatus()==1) {
			return -1;
		}
		int i=0;
		for(;i<ParkingLot.maxSize;i++) {
			if(pl1.slots[i].getStatus()==0) {
				break;
			}
		}
		setAllotedSlot(pl1.getSlots()[i]);
		pl1.getSlots()[i].setStatus(1);
		pl1.getSlots()[i].setAllotedCar(this);
		pl1.updateStatus();
		return i;
	}
}
