package com.org.tomtom.e_commerce.service.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.org.tomtom.e_commerce.service.model.cart.Cart;
import com.org.tomtom.e_commerce.util.unique_sequence_generator.AbstractRandomLongIdEntity;

@Entity
@Table(name = "order_item")
public class Order extends AbstractRandomLongIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ElementCollection(fetch = FetchType.LAZY, targetClass = Cart.class)
	@CollectionTable(name = "order_item_cart", joinColumns = @JoinColumn(name = "order_item_id", referencedColumnName = "id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"order_item_id" }))
	@Column(name = "cart_id", nullable = false)
	private Set<Cart> cart;

	@Column(name = "created_date_time", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime createdDateTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_type", nullable = false, length = 50)
	private PaymentType paymentType;

	@Column(name = "total_bill", nullable = false, columnDefinition = "BIGINT(10)")
	private Long totalBill;

	@PrePersist
	protected void onPrePersist() {
		super.onPrePersist();
		this.createdDateTime = LocalDateTime.now().atOffset(ZoneOffset.UTC).toLocalDateTime();
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
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

}
