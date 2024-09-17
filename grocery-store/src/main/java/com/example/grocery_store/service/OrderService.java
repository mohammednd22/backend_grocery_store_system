package com.example.grocery_store.service;

import com.example.grocery_store.model.Customer;
import com.example.grocery_store.model.Order;
import com.example.grocery_store.model.OrderItem;
import com.example.grocery_store.repos.OrderRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderRepo orderRepository;

  public Order createOrder(Customer customer, List<OrderItem> orderItems) {
    Order order = new Order();
    order.setCustomer(customer);
    order.setOrderItems(orderItems);
    order.setTotalAmount(orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum());

    return orderRepository.save(order);
  }

  public double getOrderTotalBalance(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));
    return order.calculateTotalBalance();
  }
}
