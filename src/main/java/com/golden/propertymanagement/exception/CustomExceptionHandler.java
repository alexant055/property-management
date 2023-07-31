package com.golden.propertymanagement.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorModel>> fieldValidationException(MethodArgumentNotValidException exp) {
		List<ErrorModel> errorModels = new ArrayList<>();
		
		exp.getBindingResult().getFieldErrors().forEach(fe -> {
			logger.debug("Inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
			logger.info("Inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
			errorModels.add(new ErrorModel(fe.getField(), fe.getDefaultMessage()));
		});
		
		return new ResponseEntity<>(errorModels, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException e) {
		String defaultMessage = "Business Exception: {} - {}";
		e.getErrors().forEach(error -> {
			logger.info(defaultMessage, error.getCode(), error.getMessage());
			logger.debug(defaultMessage, error.getCode(), error.getMessage());
			logger.warn(defaultMessage, error.getCode(), error.getMessage());
			logger.error(defaultMessage, error.getCode(), error.getMessage());
		});
		
		return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
	}
}
