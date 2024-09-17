package com.example.grocery_store.controller;

import com.example.grocery_store.model.Customer;
import com.example.grocery_store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/register")
  public Customer registerCustomer(@RequestBody Customer customer) {
    return customerService.registerCustomer(customer);
  }
}
