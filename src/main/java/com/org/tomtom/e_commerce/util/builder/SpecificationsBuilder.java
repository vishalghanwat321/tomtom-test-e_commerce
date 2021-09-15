package com.org.tomtom.e_commerce.util.builder;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationsBuilder<T> {

	private Specification<T> specifications;
	private boolean isEmpty = true;

	public SpecificationsBuilder<T> addSpecification(Specification<T> specification) {
		if (null == specification)
			return this;
		if (isEmpty) {
			specifications = Specification.where(specification);
			isEmpty = false;
		} else
			specifications = specifications.and(specification);
		return this;
	}

	public Specification<T> build() {
		return specifications;
	}
}
