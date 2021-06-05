package com.example.demo;
import java.util.*;
import lombok.Data;
import org.springframework.stereotype.*;

@Service
@Data
public class ParkingLot {
	ParkingSlot slots[];//index serves as slot number
	int status;
	static int maxSize = 3;
	ParkingLot(){
		slots = new ParkingSlot[maxSize];
		status =0;
		init();
	}
	public void init() {                                 //initializes all slots to empty status
		for(int i=0;i<maxSize;i++) {
			slots[i] = new ParkingSlot(0);
		}
	}
	
	
	public void updateStatus() {        //update status to check if parking lot is full
		int flag = 1;
		for(int i=0;i<maxSize;i++) {
			if(slots[i].getStatus()==0) {
				flag = 0;
			}
		}
		status = flag;
	}
	
	public Car unPark(int slotNo) {                                             
			Car unparkedCar = slots[slotNo].getAllotedCar();
			if(slotNo>maxSize||unparkedCar==null) {           //if SlotNo does not exist or no car is parked at that slot, return null
				return null;
			}
			slots[slotNo].allotedCar.setAllotedSlot(null);
			slots[slotNo].setAllotedCar(null);
			slots[slotNo].setStatus(0);
			updateStatus();
			return unparkedCar;
		}
	int getBySlot(int slotNo) {                                          
		return slots[slotNo].getAllotedCar().getCarID();
	}
	int getByCarID(int carID) {
		int i=0;
		for(;i<maxSize;i++) {
			if(getSlots()[i].getAllotedCar().carID==carID) {
				break;
			}
		}
		if(i==maxSize) {
			return -1;
		}
		return i;
	}
}