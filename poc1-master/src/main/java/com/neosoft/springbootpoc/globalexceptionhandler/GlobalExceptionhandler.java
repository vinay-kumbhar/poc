package com.neosoft.springbootpoc.globalexceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.neosoft.springbootpoc.exception.InvalidId;
import com.neosoft.springbootpoc.exception.InvalidName;
import com.neosoft.springbootpoc.exception.InvalidUser;
import com.neosoft.springbootpoc.response.DataResponse;

@ControllerAdvice
public class GlobalExceptionhandler {

	@ExceptionHandler(InvalidUser.class)
	public ResponseEntity<?> invalidUserHandling(InvalidUser exception, WebRequest webRequest) {
		DataResponse dataResponse = new DataResponse(exception.getMessage(), HttpStatus.BAD_REQUEST,
				webRequest.getDescription(false), new Date());
		return new ResponseEntity<>(dataResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationnErrorhandling(MethodArgumentNotValidException argumentNotValidException) {

		DataResponse dataResponse = new DataResponse("validation error", null,
				argumentNotValidException.getBindingResult().getFieldError().getDefaultMessage(), new Date());
		return new ResponseEntity<>(dataResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidName.class)
	public ResponseEntity<?> invalidnameHandling(InvalidName invalidName, WebRequest webRequest) {
		DataResponse dataResponse = new DataResponse(invalidName.getMessage(), HttpStatus.BAD_REQUEST,
				webRequest.getDescription(false), new Date());
		return new ResponseEntity<>(dataResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InvalidId.class)
	public ResponseEntity<?> invalididHandling(InvalidId invalidId, WebRequest request) {
		DataResponse dataResponse = new DataResponse(invalidId.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false), new Date());
		return new ResponseEntity<>(dataResponse, HttpStatus.BAD_REQUEST);
	}
}
