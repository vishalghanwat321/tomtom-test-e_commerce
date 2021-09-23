package com.org.tomtom.e_commerce.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.tomtom.e_commerce.repository.ProductRepository;
import com.org.tomtom.e_commerce.service.model.product.Product;
import com.org.tomtom.e_commerce.service.model.product.ProductStatus;
import com.org.tomtom.e_commerce.util.AppConstant;
import com.org.tomtom.e_commerce.util.exception.ProductNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public Product queryProductId(Long id) throws IllegalArgumentException, ProductNotFoundException {
		if (Objects.isNull(id))
			throw new IllegalArgumentException("Product id cannot be null / empty...");
		return this.productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(AppConstant.ERROR_MESSAGE_PRODUCT_NOT_FOUND));
	}

	public Boolean checkDeletedProduct(Long id) throws IllegalArgumentException, ProductNotFoundException {
		Boolean result = false;
		Product product = this.queryProductId(id);
		if (Objects.nonNull(product) && Objects.nonNull(product.getProductStatus())
				&& ProductStatus.DELETED.toString().equalsIgnoreCase(product.getProductStatus())) {
			result = true;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Iterable<Product> query(Specification<Product> specification) {
		return this.productRepository.findAll(specification);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Product> query(Specification<Product> specification, Pageable pageable) {
		return this.productRepository.findAll(specification, pageable);
	}

	public Product save(Product product) {
		return this.productRepository.save(product);
	}

}
