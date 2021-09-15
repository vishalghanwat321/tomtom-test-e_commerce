package com.org.tomtom.e_commerce.util.mapper;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.tomtom.e_commerce.dto.ProductDto;
import com.org.tomtom.e_commerce.service.model.product.Product;

@Component
public class ProductToProductDtoMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductToProductDtoMapper.class);

	@Autowired
	private ModelMapper modelMapper;

	@PostConstruct
	protected void onPostConstruct() {
		this.initializeTypeMaps();
	}

	private TypeMap<Product, ProductDto> typeMapDTO;

	private void initializeTypeMaps() {
		LOGGER.info("initializing mapper to map Product to ProductDto...");

		Converter<Set<String>, Set<String>> converterApplicationsToIDs = ctx -> Objects.isNull(ctx.getSource()) ? null
				: ctx.getSource().stream().filter(Objects::nonNull).collect(Collectors.toSet());

		this.typeMapDTO = this.modelMapper.createTypeMap(Product.class, ProductDto.class)
				.addMapping(Product::getId, ProductDto::setId)
				.addMapping(Product::getProductName, ProductDto::setProductName)
				.addMapping(Product::getProductType, ProductDto::setProductType)
				.addMapping(Product::getProductDescription, ProductDto::setProductDescription)
				.addMapping(Product::getProductPrice, ProductDto::setProductPrice)
				.addMapping(Product::getProductSubType, ProductDto::setProductSubType)
				.addMapping(Product::getProductQuantity, ProductDto::setProductQuantity)
				.addMapping(Product::getProductBrandName, ProductDto::setProductBrandName)
				.addMapping(Product::getCreatedDateTime, ProductDto::setCreatedDateTime)
				.addMapping(Product::getModifiedDateTime, ProductDto::setModifiedDateTime)
				.addMapping(Product::getProductQuantity, ProductDto::setProductQuantity)
				.addMappings(mapper -> mapper.using(converterApplicationsToIDs).map(Product::getProductColor,
						ProductDto::setProductColor))
				.addMappings(mapper -> mapper.using(converterApplicationsToIDs).map(Product::getProductUserGender,
						ProductDto::setProductUserGender));
	}

	public ProductDto convert(Product entity) {
		if (Objects.isNull(entity))
			return null;
		return this.typeMapDTO.map(entity);
	}
}
