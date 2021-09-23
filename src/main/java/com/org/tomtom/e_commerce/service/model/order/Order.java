package com.org.tomtom.e_commerce.service.model.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.org.tomtom.e_commerce.service.model.cart.Cart;
import com.org.tomtom.e_commerce.util.unique_sequence_generator.AbstractRandomLongIdEntity;

@Entity
@Table(name = "order_item") // some issue with h2 database where we can't define table name as Order
public class Order extends AbstractRandomLongIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "cart_id", unique = true)
	private Cart cart;

	@Column(name = "created_date_time", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime createdDateTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_type", nullable = false, length = 50)
	private PaymentType paymentType;

	@Column(name = "total_bill", nullable = false, columnDefinition = "BIGINT")
	private Long totalBill;

	@Column(name = "delivery_date", nullable = false, columnDefinition = "DATE")
	private LocalDate deliveryDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "delivery_status", nullable = false, length = 50)
	private DeliveryStatus deliveryStatus;

	@PrePersist
	protected void onPrePersist() {
		super.onPrePersist();
		this.deliveryStatus = DeliveryStatus.ORDERED;
		this.createdDateTime = LocalDateTime.now().atOffset(ZoneOffset.UTC).toLocalDateTime();
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Long getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Long totalBill) {
		this.totalBill = totalBill;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
