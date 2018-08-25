package com.wealth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wealth.common.exception.BusinessException;
import com.wealth.resource.Message;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
    public ResponseEntity<Message> exceptionHandler(Exception ex) {
		Message error = new Message();
	    error.setType(HttpStatus.INTERNAL_SERVER_ERROR.toString());
	    error.setDescription("Please contact your administrator");
	    return new ResponseEntity<Message>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@ExceptionHandler(BusinessException.class)
    public ResponseEntity<Message> businessExceptionHandler(Exception ex) {
		Message error = new Message();
	    error.setType(HttpStatus.BAD_REQUEST.toString());
	    error.setDescription(ex.getMessage());
	    return new ResponseEntity<Message>(error, HttpStatus.BAD_REQUEST);
    }

}
