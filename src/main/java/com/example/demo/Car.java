package com.example.demo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
@Data
@RequiredArgsConstructor
public class Car {
	@NonNull int carID;
	ParkingSlot allotedSlot;
	
	
	
	int park(ParkingLot parkingLot) {                 //park method - Parameter: Parking Lot object, Returns SlotNo where the cark is parked
		
		if(parkingLot.getStatus()==1) {
			return -1;
		}
		int i=0;
		for(;i<ParkingLot.maxSize;i++) {
			if(parkingLot.slots[i].getStatus()==0) {
				break;
			}
		}
		setAllotedSlot(parkingLot.getSlots()[i]);
		parkingLot.getSlots()[i].setStatus(1);
		parkingLot.getSlots()[i].setAllotedCar(this);
		parkingLot.updateStatus();
		return i;
	}
}
