package com.badal.orderservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badal.orderservice.dto.OrderRequest;
import com.badal.orderservice.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	private ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		this.orderService.placeOrder(orderRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body("order saved in db");
	}

}
