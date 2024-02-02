package com.example.productservice.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.productservice.Service.InventoryService;
import com.example.productservice.Service.ProductService;
import com.example.productservice.controllers.AdminProductController;
import com.example.productservice.model.Category;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Price;
import com.example.productservice.model.Product;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AdminProductControllerTest {

        @Mock
        private ProductService productService;

        @Mock
        private InventoryService inventoryService;

        @InjectMocks
        private AdminProductController adminProductController;

        private Product product;
        private Inventory inventory;

        private Price price;
        private Category category;

        @Before
        public void setUp() {

            price = new Price();
            price.setPriceid(1);
            price.setCurrency("INR");
            price.setAmount(100.0);

            inventory = new Inventory();
            inventory.setId(1);
            inventory.setAvailable(50);
            inventory.setReserved(50);
            inventory.setTotal(100);

            category= new Category();
            category.setCid(1);
            category.setCdesc("testdesc");
            category.setImageurl("test-url");
            category.setCname("testCategory");

            product = new Product();
            product.setProductId(1);
            product.setProductName("Test Product");
            product.setProductPrice(price);
            product.setProductDescription("Test Description");
            product.setInventory(inventory);
            product.setCategory(category);

            when(productService.addProduct(product)).thenReturn(product);
            when(productService.getProductById(1)).thenReturn(product);
            when(inventoryService.addInventory(inventory)).thenReturn(inventory);
            when(inventoryService.getInventoryById(1)).thenReturn(inventory);
        }

        @Test
        public void testAddProduct() {
            ResponseEntity<Product> responseEntity = adminProductController.addProduct(product);

            assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
            assertEquals(product, responseEntity.getBody());

            verify(productService, times(1)).addProduct(product);
            verify(inventoryService, times(1)).addInventory(inventory);
        }

        @Test
        public void testDeleteProduct() {
            ResponseEntity<Void> responseEntity = adminProductController.deleteProduct(1);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

            verify(productService, times(1)).deleteProduct(1);
            verify(inventoryService, times(1)).deleteInventory(1);
        }

        @Test
        public void testDeleteProductNotFound() {
            when(productService.getProductById(2)).thenReturn(null);

            ResponseEntity<Void> responseEntity = adminProductController.deleteProduct(2);

            assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

            verify(productService, times(1)).getProductById(2);
            verify(inventoryService, times(0)).deleteInventory(2);
        }
    }