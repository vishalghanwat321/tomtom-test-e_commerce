package com.org.tomtom.e_commerce.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.org.tomtom.e_commerce.util.AppConstant;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProductCreateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "productName", required = true)
	@NotNull(message = "Product name cannot be null/empty")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid product name, entry does not meet criteria")
	@Size(max = 45, message = "Product name should be less than 45")
	private String productName;

	@JsonProperty(value = "productType", required = true)
	@NotNull(message = "Product type cannot be null/empty")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid product type, entry does not meet criteria")
	private String productType;

	@JsonProperty(value = "productDescription", required = true)
	@NotNull(message = "Product description cannot be null/empty")
	private String productDescription;

	@JsonProperty(value = "productPrice", required = true)
	@NotNull(message = "Product price cannot be null/empty")
	@Pattern(regexp = "^[1-9]{1}[0-9]*$", message = "Invalid price, entry does not meet criteria")
	private String productPrice;

	@JsonProperty(value = "productSubType", required = true)
	@NotNull(message = "Product subtype cannot be null/empty")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid product subtype, entry does not meet criteria")
	private String productSubType;

	@JsonProperty(value = "productQuantity", required = true)
	@NotNull(message = "Product quantity cannot be null/empty")
	@Pattern(regexp = "^[1-9]{1}[0-9]*$", message = "Invalid quantity, entry does not meet criteria")
	private String productQuantity;

	@JsonProperty(value = "productBrandName", required = true)
	@NotNull(message = "Product brand name cannot be null/empty")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid product brand name, entry does not meet criteria")
	private String productBrandName;

	@JsonProperty(value = "createdDateTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime createdDateTime;

	@JsonProperty(value = "productColor", required = true)
	private Set<String> productColor;

	@JsonProperty(value = "productUserGender", required = true)
	private Set<String> productUserGender;

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

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductSubType() {
		return productSubType;
	}

	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
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

}
