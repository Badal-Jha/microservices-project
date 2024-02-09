package com.badal.orderservice.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.badal.orderservice.dto.InventoryResponse;
import com.badal.orderservice.dto.OrderItemDto;
import com.badal.orderservice.dto.OrderRequest;
import com.badal.orderservice.entites.Order;
import com.badal.orderservice.entites.OrderItem;
import com.badal.orderservice.exceptions.OutOfStockException;
import com.badal.orderservice.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final ModelMapper modelMapper;
	private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		// System.out.println(orderRequest);
		List<OrderItem> orderItems = Arrays.asList(modelMapper.map(orderRequest.getOrderItemsDto(), OrderItem[].class));

		for (OrderItem orderItem : orderItems) {
			orderItem.setOrder(order);
		}

		order.setOrderItems(orderItems);
        //collect skuCOdes
		
    	List<String>skuCodes=orderRequest.getOrderItemsDto().stream().map(OrderItemDto::getSkuCode).toList();
		
		//Map<String, Integer> skuCodeToQuantityMap =orderRequest.getOrderItemsDto().stream()
		//        .collect(Collectors.toMap(OrderItemDto::getSkuCode, OrderItemDto::getQuantity));
		
		//check in inventory service if products are available
		InventoryResponse[] inventoryResponses=webClientBuilder.build().get()
				                                            .uri("http://inventory-service/api/inventorys",uriBuilder->uriBuilder.queryParam("skuCodes",skuCodes)
				                                              .build())
				                                            .retrieve()
				                                            .bodyToMono(InventoryResponse[].class)
				                                            .block();
	  
		Map<String, Integer> mp = new HashMap<>();

		// Populate the map using a forEach loop
		Arrays.stream(inventoryResponses)
		        .forEach(response -> mp.put(response.getSkuCode(), response.getQuantity()));
	    
		List<OrderItem> outOfStockItems = orderItems.stream()
		        .filter(orderItem ->
		                mp.getOrDefault(orderItem.getSkuCode(), 0) < orderItem.getQuantity())
		        .collect(Collectors.toList());
		
	    
      
		if(outOfStockItems.isEmpty())
		{orderRepository.save(order);}
		else {
			String outOfStockProducts = outOfStockItems.stream()
		            .map(OrderItem::getSkuCode)
		            .collect(Collectors.joining(", "));

			throw new OutOfStockException("Products out of stock: " + outOfStockProducts);
		
		}

	}
}
