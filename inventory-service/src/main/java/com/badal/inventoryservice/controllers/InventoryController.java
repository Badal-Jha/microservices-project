package com.badal.inventoryservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.badal.inventoryservice.dto.InventoryResponse;
import com.badal.inventoryservice.services.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventorys")
@Slf4j
public class InventoryController {

	private final InventoryService inventoryService;

//	@GetMapping("/{sku-code}")
//	public ResponseEntity<Boolean> isInStock(@PathVariable("sku-code") String skuCOde) {
//
//		return ResponseEntity.status(HttpStatus.OK).body(inventoryService.isInStock(skuCOde));
//	}
	@GetMapping
	 public List<InventoryResponse> isInStock(@RequestParam("skuCodes") List<String> skuCodes) {
		log.info("Received inventory check request for skuCode: {}", skuCodes);
        return inventoryService.isInStock(skuCodes);
    }
	
}
