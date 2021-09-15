package com.org.tomtom.e_commerce.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.tomtom.e_commerce.dto.ProductDto;
import com.org.tomtom.e_commerce.dto.request.ProductCreateDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {

	@PostMapping(path = "/{sellerId:[0-9]+}/product")
	public ProductDto addProduct(@PathVariable("sellerId") Long sellerId,
			@RequestBody @Valid ProductCreateDto productCreateDto) {

		return null;
	}

	@PutMapping(path = "/{sellerId:[0-9]+}/product/{productId:[0-9]+}")
	public ProductDto modifyProduct(@PathVariable("sellerId") Long sellerId,
			@PathVariable("productId") Long productId) {
		return null;

	}

	@DeleteMapping(path = "/{sellerId:[0-9]+}/product/{productId:[0-9]+}")
	public void deleteProduct(@PathVariable("sellerId") Long sellerId, @PathVariable("productId") Long productId) {

	}
}
