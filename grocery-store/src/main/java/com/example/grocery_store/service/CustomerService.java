package com.example.grocery_store.service;

import com.example.grocery_store.model.Customer;
import com.example.grocery_store.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepo customerRepository;

  public Customer registerCustomer(Customer customer) {
    return customerRepository.save(customer);
  }
}
