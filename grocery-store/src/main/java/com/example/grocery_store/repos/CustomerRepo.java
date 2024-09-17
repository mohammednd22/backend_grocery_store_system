package com.example.grocery_store.repos;

import com.example.grocery_store.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
  Customer findByEmail(String email);
}
