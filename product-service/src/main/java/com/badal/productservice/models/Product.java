package com.badal.productservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "Product")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

	@Id
	@Field(name = "productId")
	private String id;
	private String name;
	private String description;
	private double price;
	private String category;
	private String brand;

}
