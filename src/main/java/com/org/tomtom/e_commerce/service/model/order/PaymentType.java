package com.org.tomtom.e_commerce.service.model.order;

import java.util.Objects;

public enum PaymentType {

	WALLET("WALLET"), 
	UPI("UPI"), 
	NET_BANKING("NET_BANKING"), 
	CASH_ON_DELIVERY("CASH_ON_DELIVERY"),
	DEBIT_CARD("DEBIT_CARD"), 
	CREDIT_CARD("CREDIT_CARD");

	String value;

	PaymentType(String value) {
		this.value = value;
	}

	public String getFromValue(PaymentType paymentType) {
		return Objects.nonNull(paymentType) ? paymentType.getValue() : null;
	}

	public String getValue() {
		return value;
	}

}
