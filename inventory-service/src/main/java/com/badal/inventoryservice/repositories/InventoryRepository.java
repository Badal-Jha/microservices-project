package com.badal.inventoryservice.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badal.inventoryservice.entities.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	 List<Inventory> findBySkuCodeIn(List<String> skuCode);

	 
}
