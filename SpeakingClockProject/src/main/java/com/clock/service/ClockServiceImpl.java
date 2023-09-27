package com.clock.service;

import org.springframework.stereotype.Service;
import com.clock.ExceptionHandler.InvalidHourException;
import com.clock.ExceptionHandler.InvalidMinuteException;
import com.clock.ExceptionHandler.InvalidTimeException;

@Service
public class ClockServiceImpl implements ClockService {
	
	private static final String[] ZeroToNineteenArray = {
			"zero","one","two","three","four", "five","six","seven",
			"eight","nine","ten", "eleven","twelve","thirteen","fourteen",
			"fifteen", "sixteen","seventeen","eighteen","nineteen"};
	
	private static final String[] TenArray = {"","","twenty","thirty","forty","fifty"};

	@Override
	public String convertTimeToWords(String time) throws InvalidTimeException {
		this.TimeValidation(time);
		
		StringBuilder sb = new StringBuilder("It's ");
		
		// Check for Midday --->
		if(time.equals("12:00")) {
			sb.append("Midday");
		}
		
		// Check for Midnight --->
		else if(time.equals("00:00")) {
			sb.append("Midnight");
		}
		
		else {
			String[] splitTime = time.split(":");
			
			// Converting hours into words --->
			Integer hours = Integer.parseInt(splitTime[0]);
			String hourString = this.convertInt(hours);
			sb.append(hourString + " ");
			
			// Converting minutes into words --->
			Integer minutes = Integer.parseInt(splitTime[1]);
			String minuteString = this.convertInt(minutes);
			sb.append(minuteString);
		}
		return sb.toString();
	}
	
	// Method for Converting given Integer into words --->
	private String convertInt(final Integer number) { // 21
		if(number < 20) return ZeroToNineteenArray[number];
		return TenArray[number/10] + ((number % 10 > 0) ? " " + convertInt(number % 10):""); //
	}

	// Method for validation of I/P Time --->
	private void TimeValidation(final String time) throws InvalidTimeException {
		
		// Checking length & colon of Input String
		if(time.length() != 5 || !time.contains(":")) {
			throw new InvalidTimeException();
		}
		
		//Checking Hours are an Integer ->>
		String[] splitTime =time.split(":");
		Integer hours = new Integer(0);
		try {
			hours = Integer.parseInt(splitTime[0]);
		}
		catch(NumberFormatException e) {
			throw new InvalidHourException();
		}
		
		//Checking Hours are in between 0-23 ->>
		if(hours < 0 || hours > 23) {
			throw new InvalidHourException();
		}
		
		//Checking Minutes are an Integer ->>
		Integer minutes = new Integer(0);
		try {
			minutes = Integer.parseInt(splitTime[1]);
		}
		catch(NumberFormatException e) {
			throw new InvalidMinuteException();
		}
			
		//Checking Minutes are in between 0-59 ->>
		if(minutes < 0 || minutes > 59) {
			throw new InvalidMinuteException();
		}	
	}
}