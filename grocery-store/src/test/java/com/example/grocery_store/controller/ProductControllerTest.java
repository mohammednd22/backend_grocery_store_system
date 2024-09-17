package com.example.grocery_store.controller;

import com.example.grocery_store.model.Product;
import com.example.grocery_store.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  private MockMvc mockMvc;

  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
  }

  @Test
  public void testGetProductById() throws Exception {
    Product product = new Product();
    product.setId(1L);
    product.setName("Product 1");
    product.setPrice(10.0);

    when(productService.findById(1L)).thenReturn(Optional.of(product));

    mockMvc.perform(get("/products/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Product 1"))
        .andExpect(jsonPath("$.price").value(10.0));
  }

  @Test
  public void testCreateProduct() throws Exception {
    Product product = new Product();
    product.setId(1L);
    product.setName("Product 1");
    product.setPrice(10.0);

    when(productService.save(any(Product.class))).thenReturn(product);

    ObjectMapper objectMapper = new ObjectMapper();
    String productJson = objectMapper.writeValueAsString(product);

    mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(productJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Product 1"))
        .andExpect(jsonPath("$.price").value(10.0));
  }

  @Test
  public void testDeleteProduct() throws Exception {
    doNothing().when(productService).deleteById(1L);

    mockMvc.perform(delete("/products/1"))
        .andExpect(status().isOk());
  }
}
