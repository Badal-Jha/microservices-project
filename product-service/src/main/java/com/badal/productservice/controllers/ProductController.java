package com.badal.productservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badal.productservice.dto.ProductRequest;
import com.badal.productservice.dto.ProductResponse;
import com.badal.productservice.models.Product;
import com.badal.productservice.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {

		productService.createProduct(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getProducts() {
		List<Product> products = this.productService.getAllProducts();
		List<ProductResponse> productResponses = Arrays.asList(modelMapper.map(products, ProductResponse[].class));

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productResponses);

	}
}
