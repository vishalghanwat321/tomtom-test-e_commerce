package com.org.tomtom.e_commerce.service.model.product;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
		return Optional.ofNullable(productTypes).map(items -> StreamSupport.stream(items.spliterator(), false)
				.filter(Objects::nonNull).collect(Collectors.toSet())).map(pattern -> {
					if (pattern.isEmpty())
						return emptyAnd();
					return (Specification<Product>) (root, query, cb) -> cb.like(
							cb.lower(root.get(Product_.PRODUCT_TYPE)), String.format("%%%s%%", pattern).toLowerCase());
				}).orElse(emptyAnd());
	}

	public static Specification<Product> filterByProductBrandName(Iterable<String> productBrandNames) {
		return Optional.ofNullable(productBrandNames).map(items -> StreamSupport.stream(items.spliterator(), false)
				.filter(Objects::nonNull).collect(Collectors.toSet())).map(pattern -> {
					if (pattern.isEmpty())
						return emptyAnd();
					return (Specification<Product>) (root, query, cb) -> cb.like(
							cb.lower(root.get(Product_.PRODUCT_SUB_TYPE)),
							String.format("%%%s%%", pattern).toLowerCase());
				}).orElse(emptyAnd());
	}

	public static Specification<Product> filterByProductSubTypes(Iterable<String> productSubTypes) {
		return Optional.ofNullable(productSubTypes).map(items -> StreamSupport.stream(items.spliterator(), false)
				.filter(Objects::nonNull).collect(Collectors.toSet())).map(pattern -> {
					if (pattern.isEmpty())
						return emptyAnd();
					return (Specification<Product>) (root, query, cb) -> cb.like(
							cb.lower(root.get(Product_.PRODUCT_BRAND_NAME)),
							String.format("%%%s%%", pattern).toLowerCase());
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

}
