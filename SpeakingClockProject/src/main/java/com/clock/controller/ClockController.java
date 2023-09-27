package com.clock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.clock.service.ClockService;

@RestController
public class ClockController {
	
	@Autowired
	ClockService cs;
	
	@GetMapping("/clock/{time}")
	public String TimeInWords(@PathVariable String time) {
		return cs.convertTimeToWords(time);	
	}
	
//	For Blank Output ->>>
	@GetMapping("/clock/")
	public String TimeInWords2() {
		return "Please enter correct time in \"hh:mm\" format, It should not be Blank.";
	}
	
}