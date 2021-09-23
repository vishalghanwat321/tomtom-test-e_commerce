package com.org.tomtom.e_commerce.service.model.order;

import java.util.Objects;

public enum DeliveryStatus {

	ORDERED("ORDERED"), 
	SHIPPED("SHIPPED"), 
	DELIVERED("DELIVERED"), 
	CANCELLED("CANCELLED"), 
	RETURN_INTIATE("RETURN_INTIATE"),
	RETURNED("RETURNED");

	String value;

	DeliveryStatus(String value) {
		this.value = value;
	}

	public String getFromValue(DeliveryStatus status) {
		return Objects.nonNull(status) ? status.getValue() : null;
	}

	public String getValue() {
		return value;
	}
}
