package com.example.grocery_store.repos;

import com.example.grocery_store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}