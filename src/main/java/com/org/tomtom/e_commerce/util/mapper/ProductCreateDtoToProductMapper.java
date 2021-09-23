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

import com.org.tomtom.e_commerce.dto.request.ProductCreateDto;
import com.org.tomtom.e_commerce.service.model.product.Product;

@Component
public class ProductCreateDtoToProductMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCreateDtoToProductMapper.class);

	@Autowired
	private ModelMapper modelMapper;

	@PostConstruct
	protected void onPostConstruct() {
		this.initializeTypeMaps();
	}

	private TypeMap<ProductCreateDto, Product> typeMapDTO;

	private void initializeTypeMaps() {
		LOGGER.info("initializing mapper to map Product to ProductDto...");

		Converter<Set<String>, Set<String>> converterToSet = ctx -> Objects.isNull(ctx.getSource()) ? null
				: ctx.getSource().stream().filter(Objects::nonNull).collect(Collectors.toSet());

		Converter<String, Long> converToLong = ctx -> Objects.isNull(ctx.getSource()) ? 0
				: Long.valueOf(ctx.getSource());

		this.typeMapDTO = this.modelMapper.createTypeMap(ProductCreateDto.class, Product.class)
				.addMapping(ProductCreateDto::getProductName, Product::setProductName)
				.addMapping(ProductCreateDto::getProductType, Product::setProductType)
				.addMapping(ProductCreateDto::getProductDescription, Product::setProductDescription)
				.addMapping(ProductCreateDto::getProductSubType, Product::setProductSubType)
				.addMapping(ProductCreateDto::getProductBrandName, Product::setProductBrandName)
				.addMappings(mapper -> mapper.using(converToLong).map(ProductCreateDto::getProductQuantity,
						Product::setProductQuantity))
				.addMappings(mapper -> mapper.using(converToLong).map(ProductCreateDto::getProductPrice,
						Product::setProductPrice))
				.addMappings(mapper -> mapper.using(converterToSet).map(ProductCreateDto::getProductColor,
						Product::setProductColor))
				.addMappings(mapper -> mapper.using(converterToSet).map(ProductCreateDto::getProductUserGender,
						Product::setProductUserGender));
	}

	public Product convert(ProductCreateDto entity) {
		if (Objects.isNull(entity))
			return null;

		return this.typeMapDTO.map(entity);
	}
}
