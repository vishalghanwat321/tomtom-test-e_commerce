package com.org.tomtom.e_commerce.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.tomtom.e_commerce.dto.ProductDto;
import com.org.tomtom.e_commerce.dto.request.ProductCreateDto;
import com.org.tomtom.e_commerce.dto.request.ProductUpdateDto;
import com.org.tomtom.e_commerce.service.SellerService;
import com.org.tomtom.e_commerce.util.AppConstant;
import com.org.tomtom.e_commerce.util.exception.InternalApplicationException;
import com.org.tomtom.e_commerce.util.exception.ProductAlreadyExistsException;
import com.org.tomtom.e_commerce.util.exception.UserNotFoundException;
import com.org.tomtom.e_commerce.util.mapper.ProductToProductDtoMapper;

/**
 * 
 * @author Vishal Ghanwat
 * @apiNote This is for seller
 *
 */

@RestController
@RequestMapping(path = "/seller")
@CrossOrigin("*")
public class SellerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SellerController.class);

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ProductToProductDtoMapper productToProductDtoMapper;

	@PostMapping(path = "/{sellerId:[0-9]+}/product")
	public ProductDto addProduct(@PathVariable("sellerId") Long sellerId,
			@RequestBody @Valid ProductCreateDto productCreateDto) throws UserNotFoundException,
			IllegalArgumentException, InternalApplicationException, ProductAlreadyExistsException {
		LOGGER.info("SellerController ::  addProduct execution started");
		try {
			if (this.sellerService.validateActiveAndValidSeller(sellerId)) {
				return this.productToProductDtoMapper
						.convert(this.sellerService.addProduct(productCreateDto, sellerId));
			} else {
				throw new UserNotFoundException(AppConstant.ERROR_MESSAGE_USER_NOT_FOUND);
			}
		} finally {
			LOGGER.info("SellerController ::  addProduct execution completed");
		}
	}

	@PutMapping(path = "/{sellerId:[0-9]+}/product/{productId:[0-9]+}")
	public Object modifyProduct(@PathVariable("sellerId") Long sellerId, @PathVariable("productId") Long productId,
			@RequestBody @Valid ProductUpdateDto productUpdateDto) {
		LOGGER.info("SellerController ::  modifyProduct execution started");
		try {
			if (this.sellerService.validateActiveAndValidSeller(sellerId)) {
				return "Functionality yet to implement";
			} else {
				throw new UserNotFoundException(AppConstant.ERROR_MESSAGE_USER_NOT_FOUND);
			}
		} finally {
			LOGGER.info("SellerController ::  modifyProduct execution completed");
		}
	}

	@DeleteMapping(path = "/{sellerId:[0-9]+}/product/{productId:[0-9]+}")
	public String deleteProduct(@PathVariable("sellerId") Long sellerId, @PathVariable("productId") Long productId) {
		LOGGER.info("SellerController ::  deleteProduct execution started");
		try {
			if (this.sellerService.validateActiveAndValidSeller(sellerId)) {
				if (this.sellerService.deleteProduct(sellerId, productId)) {
					return "Product deleted successfully, once available you can update the product details to move from deleted to available.";
				} else {
					return "Product not deleted successfully / Product belongs to diffrent seller";
				}
			} else {
				throw new UserNotFoundException(AppConstant.ERROR_MESSAGE_USER_NOT_FOUND);
			}
		} finally {
			LOGGER.info("SellerController ::  deleteProduct execution completed");
		}
	}
}
