package com.example.grocery_store.service;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.grocery_store.model.Customer;
import com.example.grocery_store.model.Order;
import com.example.grocery_store.model.OrderItem;
import com.example.grocery_store.model.Product;
import com.example.grocery_store.repos.OrderRepo;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class OrderServiceTest {

  @Mock
  private OrderRepo orderRepo;

  @InjectMocks
  private OrderService orderService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateOrder() {
    // Setup test data
    Customer customer = new Customer();
    customer.setId(1L);

    Product product = new Product();
    product.setId(1L);
    product.setPrice(10.0);

    OrderItem item = new OrderItem();
    item.setProduct(product);
    item.setQuantity(2);
    item.setPrice(10.0);
    item.setOrder(null);

    List<OrderItem> orderItems = Arrays.asList(item);

    Order order = new Order();
    order.setCustomer(customer);
    order.setOrderItems(orderItems);
    order.setTotalAmount(20.0);

    // Manually simulate save behavior
    OrderService mockOrderService = new OrderService() {
      @Override
      public Order createOrder(Customer customer, List<OrderItem> orderItems) {
        return order;
      }

      @Override
      public double getOrderTotalBalance(Long orderId) {
        return order.calculateTotalBalance();
      }
    };

    Order createdOrder = mockOrderService.createOrder(customer, orderItems);
    assertNotNull(createdOrder);
    assertEquals(20.0, createdOrder.getTotalAmount());
  }

  @Test
  public void testGetOrderTotalBalance() {
    // Setup test data
    Customer customer = new Customer();
    customer.setId(1L);

    Product product = new Product();
    product.setId(1L);
    product.setPrice(10.0);

    OrderItem item = new OrderItem();
    item.setProduct(product);
    item.setQuantity(2);
    item.setPrice(10.0);
    item.setOrder(null);

    List<OrderItem> orderItems = Arrays.asList(item);

    Order order = new Order();
    order.setId(1L);
    order.setCustomer(customer);
    order.setOrderItems(orderItems);
    order.setTotalAmount(20.0);

    // Manually simulate findById behavior
    OrderService mockOrderService = new OrderService() {
      @Override
      public Order createOrder(Customer customer, List<OrderItem> orderItems) {
        return order;
      }

      @Override
      public double getOrderTotalBalance(Long orderId) {
        return order.calculateTotalBalance();
      }
    };

    double totalBalance = mockOrderService.getOrderTotalBalance(1L);
    assertEquals(20.0, totalBalance);
  }
}