package com.badal.productservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.badal.productservice.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
