package com.example.productservice.Controller;

import com.example.productservice.Service.ProductService;
import com.example.productservice.controllers.ProductController;
import com.example.productservice.model.Category;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Price;
import com.example.productservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductController productController;

    private Product product;
    private List<Product> products;
    private Inventory inventory;

    private Price price;
    private Category category;

    @BeforeEach
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

        category = new Category();
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

        products = new ArrayList<>();
        products.add(product);

//        when(productService.getAllProduct()).thenReturn(products);
//        when(productService.getProductById(1)).thenReturn(product);
//        when(productService.getProductByName("Test Product")).thenReturn(product);
    }

    @Test
    public void testGetAllProducts() {

        when(productService.getAllProduct()).thenReturn(products);

        ResponseEntity<?> responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());

        verify(productService, times(1)).getAllProduct();
    }

    @Test
    public void testGetAllProductsEmpty() {
        when(productService.getAllProduct()).thenReturn(new ArrayList<>());

        ResponseEntity<String> responseEntity = (ResponseEntity<String>) productController.getAllProducts();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No items in the inventory", responseEntity.getBody());

        verify(productService, times(1)).getAllProduct();
    }

    @Test
    public void testGetProductById() {

        when(productService.getProductById(1)).thenReturn(product);

        ResponseEntity<?> responseEntity = productController.getOneProductById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());

        verify(productService, times(1)).getProductById(1);
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productService.getProductById(2)).thenReturn(null);

        ResponseEntity<String> responseEntity = (ResponseEntity<String>) productController.getOneProductById(2);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No items in the inventory", responseEntity.getBody());

        verify(productService, times(1)).getProductById(2);
    }

    @Test
    public void testGetProductByName() {
        when(productService.getProductByName("Test Product")).thenReturn(product);

        ResponseEntity<?> responseEntity = productController.getAllProductsByName("Test Product");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());

        verify(productService, times(1)).getProductByName("Test Product");
    }

    @Test
    public void testGetProductByNameNotFound() {
        when(productService.getProductByName("Non-Existent Product")).thenReturn(null);

        ResponseEntity<String> responseEntity = (ResponseEntity<String>) productController.getAllProductsByName("Non-Existent Product");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No items in the inventory", responseEntity.getBody());

        verify(productService, times(1)).getProductByName("Non-Existent Product");
    }
}

//    @Test()
//    public void testGetProductByCategory() {
////        RestTemplate restTemplate = mock(RestTemplate.class);
////        Category category = new Category();
////        category.setId(1);
////        category.setName("Test Category");
//
//        doReturn(category).when(restTemplate).getForObject(anyString(), eq(Category.class));
////        when(restTemplate.getForObject(anyString(), eq(Category.class))).thenReturn(category);
//        when(productService.getAllProductByCategory(category)).thenReturn(products);
//
//        ProductController productController = new ProductController();
//
//        ResponseEntity<?> responseEntity = productController.getAllProductByCategory(1);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(products, responseEntity.getBody());
//
////        verify(restTemplate, times(1)).getForObject(anyString(), eq(Category.class));
//        verify(productService, times(1)).getAllProductByCategory(category);
//    }
//
//    @Test
//    public void testGetProductByCategoryNotFound() {
//        RestTemplate restTemplate = mock(RestTemplate.class);
//
//        when(restTemplate.getForObject("http://localhost:8810/category/1", Category.class)).thenReturn(null);
//
//        ProductController productController = new ProductController();
//
//        ResponseEntity<String> responseEntity = (ResponseEntity<String>) productController.getAllProductByCategory(1);
//
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertEquals("No items in the inventory", responseEntity.getBody());
//
//        verify(restTemplate, times(1)).getForObject("http://localhost:8810/category/1", Category.class);
//        verify(productService, times(0)).getAllProductByCategory(any());
//    }
//}
