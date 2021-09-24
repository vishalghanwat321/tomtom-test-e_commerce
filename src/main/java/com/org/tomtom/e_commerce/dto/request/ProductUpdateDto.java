package com.org.tomtom.e_commerce.dto.request;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProductUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "productDescription")
	private String productDescription;

	@JsonProperty(value = "productPrice")
	@Pattern(regexp = "^[1-9]{1}[0-9]*$", message = "Invalid price, entry does not meet criteria")
	private String productPrice;

	@JsonProperty(value = "productQuantity")
	@Pattern(regexp = "^[1-9]{1}[0-9]*$", message = "Invalid quantity, entry does not meet criteria")
	private String productQuantity;

	@JsonProperty(value = "productColor")
	private Set<String> productColor;

	@JsonProperty(value = "productStatus")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid price, entry does not meet criteria")
	private String productStatus;

	@JsonProperty(value = "productUserGender")
	private Set<String> productUserGender;

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

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Set<String> getProductColor() {
		return productColor;
	}

	public void setProductColor(Set<String> productColor) {
		this.productColor = productColor;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public Set<String> getProductUserGender() {
		return productUserGender;
	}

	public void setProductUserGender(Set<String> productUserGender) {
		this.productUserGender = productUserGender;
	}

}
