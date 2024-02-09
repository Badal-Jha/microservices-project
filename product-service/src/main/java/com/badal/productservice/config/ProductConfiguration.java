package com.badal.productservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
