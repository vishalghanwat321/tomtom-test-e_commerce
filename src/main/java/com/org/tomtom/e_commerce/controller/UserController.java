package com.org.tomtom.e_commerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.tomtom.e_commerce.service.model.order.PaymentType;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@PostMapping(path = "/addProductToCart")
	public void addProductToCart() {

	}

	@PostMapping(path = "/{userId}/placeOrder")
	public void placeOrder(
			@RequestParam(name = "paymentType", defaultValue = "CASH_ON_DELIVERY") PaymentType paymentType) {

	}

}
