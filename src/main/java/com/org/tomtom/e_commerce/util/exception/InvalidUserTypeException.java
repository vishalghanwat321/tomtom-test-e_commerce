package com.org.tomtom.e_commerce.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST)
public class InvalidUserTypeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserTypeException(String errorMessage) {
		super(errorMessage);
	}

	public InvalidUserTypeException() {
		super();
	}

}