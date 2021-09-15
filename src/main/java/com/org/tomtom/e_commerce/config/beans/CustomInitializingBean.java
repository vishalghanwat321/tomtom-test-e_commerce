package com.org.tomtom.e_commerce.config.beans;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomInitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomInitializingBean.class);

	@Bean
	public ModelMapper modelMapper() {
		LOGGER.info("Initializing ModelMapper started...");
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		LOGGER.info("Initializing ModelMapper completed...");
		return modelMapper;
	}

}