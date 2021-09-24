package com.org.tomtom.e_commerce.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.tomtom.e_commerce.dto.ProductDto;
import com.org.tomtom.e_commerce.service.ProductService;
import com.org.tomtom.e_commerce.service.model.product.Product;
import com.org.tomtom.e_commerce.service.model.product.ProductSpecs;
import com.org.tomtom.e_commerce.service.model.product.ProductStatus;
import com.org.tomtom.e_commerce.util.AppConstant;
import com.org.tomtom.e_commerce.util.builder.SpecificationsBuilder;
import com.org.tomtom.e_commerce.util.exception.ProductNotFoundException;
import com.org.tomtom.e_commerce.util.mapper.ProductToProductDtoMapper;

@RestController
@RequestMapping(path = "/product")
@CrossOrigin("*")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductToProductDtoMapper productToProductDtoMapper;

	@GetMapping(path = "/{productId:[0-9]+}")
	@ResponseBody
	public ProductDto getProductDetailsById(@PathVariable(value = "productId") Long productId)
			throws IllegalArgumentException, ProductNotFoundException {

		LOGGER.info("ProductController ::  getProductDetailsById execution started");
		try {
			Product product = this.productService.queryProductId(productId);
			if (Objects.nonNull(product) && Objects.nonNull(product.getProductStatus())
					&& ProductStatus.DELETED.toString().equalsIgnoreCase(product.getProductStatus())) {
				throw new ProductNotFoundException(AppConstant.ERROR_MESSAGE_PRODUCT_NOT_FOUND);
			}
			return this.productToProductDtoMapper.convert(product);
		} finally {
			LOGGER.info("ProductController ::  getProductDetailsById execution completed");
		}
	}

	@GetMapping(path = "/list")
	@ResponseBody
	public Iterable<ProductDto> getAllProductDetails(@RequestParam(required = false, value = "page") Integer page,
			@RequestParam(required = false, value = "page_size") Integer pageSize,
			@RequestParam(required = false, value = "sort_props[]") String[] sortProperties,
			@RequestParam(required = false, value = "sort_asc", defaultValue = "false") boolean sortAscending,
			@RequestParam(required = false, value = "product_types[]") Set<String> productTypes,
			@RequestParam(required = false, value = "product_brand_names[]") Set<String> productBrandNames,
			@RequestParam(required = false, value = "product_sub_types[]") Set<String> productSubTypes,
			@RequestParam(required = false, value = "min_price", defaultValue = "1") Long minPrice,
			@RequestParam(required = false, value = "max_price", defaultValue = "10000000") Long maxPrice,
			@RequestParam(required = false, value = "ids[]") Set<Long> ids) throws IllegalArgumentException {

		LOGGER.info("ProductController ::  getAllProductDetails execution started");
		try {

			SpecificationsBuilder<Product> specificationsBuilder = new SpecificationsBuilder<>();
			specificationsBuilder.addSpecification(ProductSpecs.filterByIds(ids));
			specificationsBuilder.addSpecification(ProductSpecs.filterByProductTypes(productTypes));
			specificationsBuilder.addSpecification(ProductSpecs.filterByProductBrandName(productBrandNames));
			specificationsBuilder.addSpecification(ProductSpecs.filterByProductSubTypes(productSubTypes));
			specificationsBuilder.addSpecification(ProductSpecs.filterByProductNotDeleted());
			specificationsBuilder.addSpecification(ProductSpecs.filterByProductQuantityMoreThanOne());
			specificationsBuilder.addSpecification(ProductSpecs.filterByPriceRange(minPrice, maxPrice));

			if (Objects.nonNull(page) && page >= 0 && Objects.nonNull(pageSize) && pageSize > 0) {
				PageRequest pageRequest = PageRequest.of(page, pageSize, sortAscending ? Direction.ASC : Direction.DESC,
						Optional.ofNullable(sortProperties)
								.map(items -> Arrays.stream(items).filter(StringUtils::isNotBlank))
								.orElse(Stream.of("id")).toArray(String[]::new));
				Page<Product> products = this.productService.query(specificationsBuilder.build(), pageRequest);
				return products.map(this.productToProductDtoMapper::convert);
			}

			Iterable<Product> products = this.productService.query(specificationsBuilder.build());
			Iterable<ProductDto> productDtoList = StreamSupport.stream(products.spliterator(), false)
					.map(this.productToProductDtoMapper::convert).collect(Collectors.toList());
			Collections.sort((List<ProductDto>) productDtoList);
			return Objects.nonNull(productDtoList) ? productDtoList : null;
		} finally {
			LOGGER.info("ProductController ::  getAllProductDetails execution completed");
		}
	}
}