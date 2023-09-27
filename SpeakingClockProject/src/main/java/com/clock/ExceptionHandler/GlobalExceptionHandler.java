package com.clock.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidTimeException.class)
	public ResponseEntity<String> handleInvalidTimeException(InvalidTimeException excp){
		return new ResponseEntity<String>("Invalid Time Format, It Should be in \"hh:mm\" format.", HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(InvalidHourException.class)
	public ResponseEntity<String> handleInvalidHourException(InvalidHourException excp){
		return new ResponseEntity<String>("Invalid Hours, Please Enter Correct Hours", HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(InvalidMinuteException.class)
	public ResponseEntity<String> handleInvalidMinuteException(InvalidMinuteException excp){
		return new ResponseEntity<String>("Invalid Minutes, Please Enter Correct Minutes", HttpStatus.BAD_REQUEST);	
	}
}