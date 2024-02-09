package com.badal.orderservice.dto;

import java.math.BigDecimal;

import com.badal.orderservice.entites.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
	private Order order;
}
