package com.org.tomtom.e_commerce.service.model.cart;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.org.tomtom.e_commerce.service.model.product.Product;
import com.org.tomtom.e_commerce.util.unique_sequence_generator.AbstractRandomLongIdEntity;

@Entity
@Table(name = "cart")
@Cacheable(true)
public class Cart extends AbstractRandomLongIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ElementCollection(fetch = FetchType.EAGER, targetClass = Product.class)
	@CollectionTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"cart_id" }))
	@Column(name = "product_id", nullable = false)
	private Set<Product> product;

	@Column(name = "modified_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime modifiedDateTime;

	@PrePersist
	protected void onPrePersist() {
		super.onPrePersist();
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public LocalDateTime getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

}
