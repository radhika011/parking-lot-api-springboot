package com.example.demo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
@Data
@RequiredArgsConstructor
public class ParkingSlot {
	//add alloted car
	@NonNull int status;//1 if full, 0 if empty
	Car allotedCar;
	/*ParkingSlot(int status){
		allotedCar = null;
		this.status = status;
	}
	*/
	
	
	
}