package com.example.grocery_store.repos;

import com.example.grocery_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {


}
