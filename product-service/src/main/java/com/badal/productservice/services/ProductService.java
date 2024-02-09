package com.badal.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.badal.productservice.dto.ProductRequest;
import com.badal.productservice.models.Product;
import com.badal.productservice.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {

		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).brand(productRequest.getBrand()).category("phone").build();
		productRepository.save(product);

		log.info("product {} is saved", product.getId());
	}

	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}
}
