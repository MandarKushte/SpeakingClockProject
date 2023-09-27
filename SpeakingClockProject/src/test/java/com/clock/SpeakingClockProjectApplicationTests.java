package com.clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import com.clock.ExceptionHandler.InvalidHourException;
import com.clock.ExceptionHandler.InvalidMinuteException;
import com.clock.ExceptionHandler.InvalidTimeException;
import com.clock.service.ClockServiceImpl;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class SpeakingClockProjectApplicationTests {
	
private ClockServiceImpl cs;
	
	@BeforeAll
	public void setUp() {cs = new ClockServiceImpl();}
	
	// Correct I/P Testing --->
	@Test
	public void Test1() {
		String time = "12:00";
		assertEquals("It's Midday", cs.convertTimeToWords(time));
	}
	
	@Test
	public void Test2() {
		String time = "00:00";
		assertEquals("It's Midnight", cs.convertTimeToWords(time));
	}
	
	@Test
	public void Test3() {
		String time = "10:00";
		assertEquals("It's ten zero", cs.convertTimeToWords(time));
	}

	@Test
	public void Test4() {
		String time = "23:49";
		assertEquals("It's twenty three forty nine", cs.convertTimeToWords(time));
	}
	
	
	@Test
	public void Test5() {
		String time = "23:00";
		assertEquals("It's twenty three zero", cs.convertTimeToWords(time));
	}

	// Correct Exception Testing --->
	@Test
	public void Test6() {
		String time = "aa:bb";
		assertThrows(InvalidHourException.class, ()-> cs.convertTimeToWords(time));
	}
	
	@Test
	public void Test7() {
		String time = "24:15";
		assertThrows(InvalidHourException.class, ()-> cs.convertTimeToWords(time));
	}
	
	@Test
	public void Test8() {
		String time = "23:60";
		assertThrows(InvalidMinuteException.class, ()-> cs.convertTimeToWords(time));
	}
	
	@Test
	public void Test9() {
		String time = "hsbjkkh";
		assertThrows(InvalidTimeException.class, ()-> cs.convertTimeToWords(time));
	}
	
	@Test
	public void Test10() {
		String time = "15:57846";
		assertThrows(InvalidTimeException.class, ()-> cs.convertTimeToWords(time));
	}
	
	@Test
	public void Test11() {
		String time = "121655:57";
		assertThrows(InvalidTimeException.class, ()-> cs.convertTimeToWords(time));
	}
	
	@Test
	public void Test12() {
		String time = "";
		assertThrows(InvalidTimeException.class, ()-> cs.convertTimeToWords(time));
	}
}