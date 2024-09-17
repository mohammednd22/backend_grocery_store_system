package com.example.grocery_store.service;

import com.example.grocery_store.model.Product;
import com.example.grocery_store.repos.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

  @Mock
  private ProductRepo productRepo;

  @InjectMocks
  private ProductServiceImplementation productService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFindAll() {
    Product product1 = new Product();
    product1.setId(1L);
    product1.setName("Product 1");
    product1.setPrice(10.0);

    Product product2 = new Product();
    product2.setId(2L);
    product2.setName("Product 2");
    product2.setPrice(20.0);

    when(productRepo.findAll()).thenReturn(Arrays.asList(product1, product2));

    List<Product> products = productService.findAll();
    assertEquals(2, products.size());
    assertEquals("Product 1", products.get(0).getName());
    assertEquals("Product 2", products.get(1).getName());
  }

  @Test
  public void testFindById() {
    Product product = new Product();
    product.setId(1L);
    product.setName("Product 1");
    product.setPrice(10.0);

    when(productRepo.findById(1L)).thenReturn(Optional.of(product));

    Optional<Product> foundProduct = productService.findById(1L);
    assertTrue(foundProduct.isPresent());
    assertEquals("Product 1", foundProduct.get().getName());
  }

  @Test
  public void testSave() {
    Product product = new Product();
    product.setId(1L);
    product.setName("Product 1");
    product.setPrice(10.0);

    when(productRepo.save(any(Product.class))).thenReturn(product);

    Product savedProduct = productService.save(product);
    assertNotNull(savedProduct);
    assertEquals("Product 1", savedProduct.getName());
  }

  @Test
  public void testDeleteById() {
    doNothing().when(productRepo).deleteById(1L);

    productService.deleteById(1L);

    verify(productRepo, times(1)).deleteById(1L);
  }
}