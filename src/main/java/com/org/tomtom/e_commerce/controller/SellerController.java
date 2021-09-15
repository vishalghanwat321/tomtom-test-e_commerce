package com.org.tomtom.e_commerce.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {

	@PostMapping(path = "/{sellerId}/product")
	public void addProduct() {

	}

	@PutMapping(path = "/{sellerId}/product/{productId}")
	public void modifyProduct() {

	}

	@DeleteMapping(path = "/{sellerId}/product/{productId}")
	public void deleteProduct() {

	}
}
