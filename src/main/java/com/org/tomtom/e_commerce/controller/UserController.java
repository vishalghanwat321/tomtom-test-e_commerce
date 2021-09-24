package com.org.tomtom.e_commerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

//TODO functionality is yet to complete	

	@PostMapping(path = "/{userId:[0-9]+}/addProductToCart")
	public String addProductToCart(@PathVariable("userId") Long sellerId) {
		return "Functionality yet to implement";
	}

	@PostMapping(path = "/{userId:[0-9]+}/placeOrder")
	public String placeOrder(@PathVariable("userId") Long userId,
			@RequestParam(name = "paymentType", defaultValue = "CASH_ON_DELIVERY") PaymentType paymentType) {
		return "Functionality yet to implement";
	}

	@GetMapping(path = "/{userId:[0-9]+}/getOrderDetails/{orderId:[0-9]+}")
	public String getOrderDetails(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
		return "Functionality yet to implement";
	}

	@PutMapping(path = "/{userId:[0-9]+}/getOrderDetails/{orderId:[0-9]+}")
	public String modifyOrderTocancelOrReturn(@PathVariable("userId") Long userId,
			@PathVariable("orderId") Long orderId) {
		return "Functionality yet to implement";
	}

}
