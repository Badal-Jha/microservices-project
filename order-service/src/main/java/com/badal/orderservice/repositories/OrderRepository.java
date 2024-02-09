package com.badal.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badal.orderservice.entites.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
