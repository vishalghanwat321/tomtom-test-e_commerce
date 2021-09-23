package com.org.tomtom.e_commerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.tomtom.e_commerce.service.model.order.PaymentType;

/**
 * 
 * @author Vishal Ghanwat
 * @apiNote This is for buyer 
 *
 */
@RestController
@RequestMapping(path = "/user")
@CrossOrigin("*")
public class UserController {

	@PostMapping(path = "/{userId:[0-9]+}/addProductToCart")
	public void addProductToCart() {

	}

	@PostMapping(path = "/{userId:[0-9]+}/placeOrder")
	public void placeOrder(
			@RequestParam(name = "paymentType", defaultValue = "CASH_ON_DELIVERY") PaymentType paymentType) {

	}

	@GetMapping(path = "/{userId:[0-9]+}/getOrderDetails/{orderId:[0-9]+}")
	public void getOrderDetails() {

	}

	@PutMapping(path = "/{userId:[0-9]+}/getOrderDetails/{orderId:[0-9]+}")
	public void modifyOrderTocancelOrReturn() {

	}

}
