package com.org.tomtom.e_commerce.service.model.product;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.org.tomtom.e_commerce.util.unique_sequence_generator.AbstractRandomLongIdEntity_;

@StaticMetamodel(Product.class)
public class Product_ extends AbstractRandomLongIdEntity_ {

	public static volatile SingularAttribute<Product, String> productType;

	public static volatile SingularAttribute<Product, String> productSubType;

	public static volatile SingularAttribute<Product, String> productBrandName;

	public static volatile SingularAttribute<Product, String> productStatus;

	public static volatile SingularAttribute<Product, Long> productPrice;

	public static volatile SingularAttribute<Product, Long> productQuantity;
	
	public static volatile SingularAttribute<Product, String> productUserGender;

	public static final String PRODUCT_TYPE = "productType";

	public static final String PRODUCT_SUB_TYPE = "productSubType";

	public static final String PRODUCT_BRAND_NAME = "productBrandName";

	public static final String PRODUCT_PRICE = "productPrice";

	public static final String PRODUCT_STATUS = "productStatus";
	
	public static final String PRODUCT_QUANTITY = "productQuantity";

}
