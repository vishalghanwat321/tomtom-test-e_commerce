package com.org.tomtom.e_commerce.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.tomtom.e_commerce.dto.request.ProductCreateDto;
import com.org.tomtom.e_commerce.repository.UserRepository;
import com.org.tomtom.e_commerce.service.model.product.Product;
import com.org.tomtom.e_commerce.service.model.product.ProductStatus;
import com.org.tomtom.e_commerce.service.model.user.User;
import com.org.tomtom.e_commerce.service.model.user.UserStatus;
import com.org.tomtom.e_commerce.service.model.user.UserType;
import com.org.tomtom.e_commerce.util.AppConstant;
import com.org.tomtom.e_commerce.util.exception.InternalApplicationException;
import com.org.tomtom.e_commerce.util.exception.InvalidUserTypeException;
import com.org.tomtom.e_commerce.util.exception.ProductAlreadyExistsException;
import com.org.tomtom.e_commerce.util.exception.ProductNotFoundException;
import com.org.tomtom.e_commerce.util.exception.UserNotFoundException;
import com.org.tomtom.e_commerce.util.mapper.ProductCreateDtoToProductMapper;

@Service
public class SellerService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	ProductService productService;

	@Autowired
	private ProductCreateDtoToProductMapper productCreateDtoToProductMapper;

	@Transactional(readOnly = true)
	public User queryUserId(Long id) throws IllegalArgumentException, UserNotFoundException {
		if (Objects.isNull(id))
			throw new IllegalArgumentException("User id cannot be null / empty...");
		return this.userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(AppConstant.ERROR_MESSAGE_USER_NOT_FOUND));
	}

	@Transactional(readOnly = true)
	public Boolean checkUserStatusAndType(Long id)
			throws IllegalArgumentException, UserNotFoundException, InvalidUserTypeException {
		Boolean result = false;
		User user = this.queryUserId(id);
		if (Objects.nonNull(user) && Objects.nonNull(user.getUserStatus())
				&& UserStatus.ACTIVE.toString().equalsIgnoreCase(user.getUserStatus().toString())) {
			if (Objects.nonNull(user) && Objects.nonNull(user.getUserType())
					&& UserType.SELLER.toString().equalsIgnoreCase(user.getUserType().toString())) {
				result = true;
			} else {
				throw new InvalidUserTypeException(AppConstant.ERROR_MESSAGE_USER_TYPE_NOT_SELLER);
			}
		}
		return result;
	}

	public boolean validateActiveAndValidSeller(Long sellerId) {
		return this.checkUserStatusAndType(sellerId);
	}

	public Product saveProduct(Product product) throws InternalApplicationException, ProductAlreadyExistsException {
		try {
			return this.productService.save(product);
		} catch (DataIntegrityViolationException | ConstraintViolationException ex) {
			throw new ProductAlreadyExistsException(AppConstant.ERROR_MESSAGE_ERROR_PRODUCT_ALREADY_EXIST);
		} catch (Exception e) {
			throw new InternalApplicationException(AppConstant.ERROR_MESSAGE_INTERNAL_ERROR_PRODUCT_NOT_ADDED);
		}
	}

	public Product addProduct(ProductCreateDto productCreateDto, Long sellerId)
			throws InternalApplicationException, ProductAlreadyExistsException {
		// TODO Auto-generated method stub
		Product product = this.productCreateDtoToProductMapper.convert(productCreateDto);
		if (Objects.nonNull(product)) {
			product.setCreatedBy(sellerId);
			product.setModifiedBy(sellerId);
			return saveProduct(product);
		} else {
			throw new InternalApplicationException(AppConstant.ERROR_MESSAGE_INTERNAL_ERROR_PRODUCT_NOT_ADDED);
		}
	}

	public boolean deleteProduct(Long sellerId, Long productId) throws ProductNotFoundException {
		Boolean result = false;
		if (!this.productService.checkDeletedProduct(productId)) {
			Product product = this.productService.queryProductId(productId);
			if (Objects.nonNull(product.getCreatedBy()) && product.getCreatedBy().equals(sellerId)) {
				product.setProductStatus(ProductStatus.DELETED.toString());
				product.setModifiedBy(sellerId);
				product.setModifiedDateTime(LocalDateTime.now().atOffset(ZoneOffset.UTC).toLocalDateTime());
				this.productService.save(product);
				result=true;
			}
		} else {
			throw new ProductNotFoundException(AppConstant.ERROR_MESSAGE_PRODUCT_NOT_FOUND);
		}
		return result;
	}
}
