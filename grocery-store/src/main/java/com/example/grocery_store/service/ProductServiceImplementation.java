package com.example.grocery_store.service;


import com.example.grocery_store.model.Product;
import com.example.grocery_store.repos.ProductRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplementation implements ProductService {

  @Autowired
  private ProductRepo productRepo;


  @Override
  public List<Product> findAll() {
    return productRepo.findAll();
  }

  @Override
  public Optional<Product> findById(Long id) {
    return productRepo.findById(id);
  }

  @Override
  public Product save(Product product) {
    return productRepo.save(product);
  }

  @Override
  public void deleteById(Long id) {
    productRepo.deleteById(id);
  }



}
