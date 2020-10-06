package com.AdrixusDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.AdrixusDemo.exception.EntityNotFoundException;

@ControllerAdvice
public class EntityExceptionController {

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<Object> customEntityNotFoundException(EntityNotFoundException ex) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
