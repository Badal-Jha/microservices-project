package com.badal.inventoryservice.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badal.inventoryservice.dto.InventoryResponse;
import com.badal.inventoryservice.repositories.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCodes) {
		 return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
				 .map(inventory-> InventoryResponse.builder()
                         .skuCode(inventory.getSkuCode())
                         .quantity(inventory.getQuantity())
                         .isInStock(inventory.getQuantity() > 0 )
                         .build()
                     )
				 .toList();
	             
	}

}
