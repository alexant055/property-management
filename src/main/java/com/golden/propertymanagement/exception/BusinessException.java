package com.golden.propertymanagement.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<ErrorModel> errors;

	public BusinessException(List<ErrorModel> errors) {
		this.errors = errors;
	}
}
