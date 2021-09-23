package com.org.tomtom.e_commerce.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.org.tomtom.e_commerce.util.AppConstant;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProductDto implements Serializable, Comparable<ProductDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String productName;

	private String productType;

	private String productDescription;

	private Long productPrice;

	private String productSubType;

	private Long productQuantity;

	private String productBrandName;

	private String productStatus;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime createdDateTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime modifiedDateTime;

	private Set<String> productColor;

	private Set<String> productUserGender;

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int compareTo(ProductDto obj) {
		return this.id.compareTo(obj.getId());
	}

}