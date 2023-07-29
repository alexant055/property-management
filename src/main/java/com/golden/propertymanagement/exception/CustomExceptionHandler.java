package com.golden.propertymanagement.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorModel>> fieldValidationException(MethodArgumentNotValidException exp) {
		List<ErrorModel> errorModels = new ArrayList<>();
		
		exp.getBindingResult().getFieldErrors().forEach(fe -> {
			errorModels.add(new ErrorModel(fe.getField(), fe.getDefaultMessage()));
		});
		
		return new ResponseEntity<>(errorModels, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException exception) {
		return new ResponseEntity<>(exception.getErrors(), HttpStatus.BAD_REQUEST);
	}
}
