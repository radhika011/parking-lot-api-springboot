package com.example.demo;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pl.pojo.tester.api.assertion.Method;
@SpringBootTest
public class DemoApplicationTests {
	@Test
	public void testPojo() {
		final Class<ParkingLot> classUnderTest1 = ParkingLot.class;
		assertPojoMethodsFor(classUnderTest1).testing(Method.GETTER,Method.SETTER,Method.TO_STRING);
		final Class<ParkingSlot> classUnderTest2 = ParkingSlot.class;
		assertPojoMethodsFor(classUnderTest2).testing(Method.GETTER,Method.SETTER,Method.TO_STRING);
		final Class<Car> classUnderTest3 = Car.class;
		assertPojoMethodsFor(classUnderTest3).testing(Method.GETTER,Method.SETTER,Method.TO_STRING);
	}
}
