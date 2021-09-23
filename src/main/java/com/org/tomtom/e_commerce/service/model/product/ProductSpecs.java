package com.org.tomtom.e_commerce.service.model.product;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {

	public static Specification<Product> filterByIds(Iterable<Long> filterIds) {
		return Optional
				.ofNullable(filterIds).map(items -> StreamSupport.stream(items.spliterator(), false)
						.filter(Objects::nonNull).collect(Collectors.toSet()))
				.map(validIds -> (Specification<Product>) (root, query, cb) -> {
					query.distinct(true);
					return root.get(Product_.id).in(validIds);
				}).orElse(emptyAnd());
	}

	public static Specification<Product> filterByProductTypes(Iterable<String> productTypes) {

		return Optional.ofNullable(productTypes)
				.map(items -> StreamSupport.stream(items.spliterator(), false).filter(StringUtils::isNotBlank)
						.collect(Collectors.toSet()))
				.map(validProductTypes -> (Specification<Product>) (root, query, cb) -> {
					query.distinct(true);
					return root.get(Product_.PRODUCT_TYPE).in(validProductTypes);
				}).orElse(emptyAnd());
	}

	public static Specification<Product> filterByProductBrandName(Iterable<String> productBrandNames) {

		return Optional.ofNullable(productBrandNames)
				.map(items -> StreamSupport.stream(items.spliterator(), false).filter(StringUtils::isNotBlank)
						.collect(Collectors.toSet()))
				.map(validProductBrandNames -> (Specification<Product>) (root, query, cb) -> {
					query.distinct(true);
					return root.get(Product_.PRODUCT_BRAND_NAME).in(validProductBrandNames);
				}).orElse(emptyAnd());
	}

	public static Specification<Product> filterByProductSubTypes(Iterable<String> productSubTypes) {

		return Optional.ofNullable(productSubTypes)
				.map(items -> StreamSupport.stream(items.spliterator(), false).filter(StringUtils::isNotBlank)
						.collect(Collectors.toSet()))
				.map(validProductSubTypes -> (Specification<Product>) (root, query, cb) -> {
					query.distinct(true);
					return root.get(Product_.PRODUCT_SUB_TYPE).in(validProductSubTypes);
				}).orElse(emptyAnd());
	}

	public static Specification<Product> filterByPriceRange(Long minPrice, Long maxPrice) {
		if (Objects.isNull(minPrice) && Objects.isNull(maxPrice))
			return emptyAnd();
		return (root, query, cb) -> cb.between(root.get(Product_.PRODUCT_PRICE), minPrice, maxPrice);
	}

	private static Specification<Product> emptyAnd() {
		return (root, query, cb) -> cb.and();
	}

	public static Specification<Product> filterByProductNotDeleted() {
		return (Specification<Product>) (root, query, cb) -> cb.notLike(cb.lower(root.get(Product_.PRODUCT_STATUS)),
				String.format("%%%s%%", ProductStatus.DELETED.toString()).toLowerCase());
	}

	public static Specification<Product> filterByProductQuantityMoreThanOne() {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Product_.PRODUCT_QUANTITY), 1);
	}

}
