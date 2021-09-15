package com.org.tomtom.e_commerce.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.tomtom.e_commerce.service.model.product.Product;

@Repository
public interface ProductRepository
		extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor, Serializable {

	Iterable<Product> findAllByOrderByIdAsc();
}
