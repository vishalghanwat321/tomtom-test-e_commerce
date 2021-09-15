package com.org.tomtom.e_commerce.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT)
public class ProductAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}

	public ProductAlreadyExistsException() {
		super();
	}

}