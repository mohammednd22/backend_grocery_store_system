package com.example.grocery_store.controller;

import com.example.grocery_store.model.Customer;
import com.example.grocery_store.model.Order;
import com.example.grocery_store.model.OrderItem;
import com.example.grocery_store.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping("/create")
  public Order createOrder(@RequestBody Customer customer, @RequestBody List<OrderItem> orderItems) {
    return orderService.createOrder(customer, orderItems);
  }

  @GetMapping("/{orderId}/total")
  public double getOrderTotal(@PathVariable Long orderId) {
    return orderService.getOrderTotalBalance(orderId);
  }
}
