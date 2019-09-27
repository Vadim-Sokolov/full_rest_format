package com.ttr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttr2.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Order save(Order order);

}
