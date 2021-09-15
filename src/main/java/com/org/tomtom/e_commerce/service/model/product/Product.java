package com.org.tomtom.e_commerce.service.model.product;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.org.tomtom.e_commerce.util.unique_sequence_generator.AbstractRandomLongIdEntity;

@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = { "product_name", "product_type",
		"product_sub_type", "product_brand_name" }))
@Cacheable(false)
public class Product extends AbstractRandomLongIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "product_name", nullable = false, length = 45)
	private String productName;

	@Column(name = "product_type", nullable = false, updatable = false)
	private String productType;

	@Column(name = "product_desc", nullable = false)
	private String productDescription;

	@Column(name = "product_price", columnDefinition = "BIGINT", nullable = false)
	private Long productPrice;

	@Column(name = "product_sub_type", nullable = false, updatable = false)
	private String productSubType;

	@Column(name = "product_quantity", columnDefinition = "BIGINT", nullable = false)
	private Long productQuantity;

	@Column(name = "product_brand_name", nullable = false, updatable = false)
	private String productBrandName;

	@Column(name = "created_date_time", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime createdDateTime;

	@Column(name = "modified_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime modifiedDateTime;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "product_color")
	private Set<String> productColor;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "product_user_gender")
	private Set<String> productUserGender;

	@PrePersist
	protected void onPrePersist() {
		super.onPrePersist();
		this.productQuantity = 1L;
		this.productPrice = 0L;
		this.createdDateTime = LocalDateTime.now().atOffset(ZoneOffset.UTC).toLocalDateTime();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductSubType() {
		return productSubType;
	}

	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public Set<String> getProductColor() {
		return productColor;
	}

	public void setProductColor(Set<String> productColor) {
		this.productColor = productColor;
	}

	public Set<String> getProductUserGender() {
		return productUserGender;
	}

	public void setProductUserGender(Set<String> productUserGender) {
		this.productUserGender = productUserGender;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public LocalDateTime getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

}
