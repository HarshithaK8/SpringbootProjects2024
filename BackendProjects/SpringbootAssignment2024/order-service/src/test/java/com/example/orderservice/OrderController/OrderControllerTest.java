//package com.example.orderservice.OrderController;
//
//import com.example.orderservice.Controller.OrderController;
//import com.example.orderservice.Model.*;
//import com.example.orderservice.Service.OrderService;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderControllerTest {
//
//    @Mock
//    private OrderService orderService;
//
//    @Mock
//    private WebClient webClient;
//
//    @InjectMocks
//    private OrderController orderController;
//
//    @Mock
//    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
//
//    @Mock
//    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;
//
//    @Mock
//    private WebClient.RequestBodySpec requestBodySpec;
//
//    @Mock
//    private WebClient.ResponseSpec responseSpec;
//
//    @Mock
//    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
//
//    @Mock
//    private Mono<Product> response;
//
//    private Product product;
//
//    private Order order;
//
//    @Mock
//    private Mono<Product> productMono;
//
//    @Mock
//    private Mono<Inventory> inventoryMono;
//
//    @Mock
//    private Mono<Void> voidMono;
//
//    @BeforeEach
//    public void setUp() {
////        this.orderController = new OrderController(orderService);
//        Price price = new Price();
//        price.setPriceid(1);
//        price.setCurrency("INR");
//        price.setAmount(100.0);
//
//        Inventory inventory = new Inventory();
//        inventory.setId(1);
//        inventory.setAvailable(50);
//        inventory.setReserved(50);
//        inventory.setTotal(100);
//
//        Category category = new Category();
//        category.setCid(1);
//        category.setCdesc("testdesc");
//        category.setImageurl("test-url");
//        category.setCname("testCategory");
//
//        product = new Product();
//        product.setProductId(1);
//        product.setProductName("Test Product");
//        product.setProductPrice(price);
//        product.setProductDescription("Test Description");
//        product.setInventory(inventory);
//        product.setCategory(category);
//
//        order = new Order();
//        order.setOrderid(1);
//        order.setProduct_name("Test Product");
//        order.setQuantity(10);
//        when(orderService.saveOrder(any(Order.class))).thenReturn(order);
//        webClient = WebClient.create("http://localhost:8080");
//
//    }
//
//    @Test
//    public void saveOrderTest_validOrder() {
//
//        when(productMono.block()).thenReturn(product);
//
//        when(responseSpec.bodyToMono(Product.class)).thenReturn(productMono);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
//        when(requestHeadersUriSpec.uri("/productByName/" + order.getProduct_name())).thenReturn(requestHeadersSpec);
//
//        when(voidMono.block()).thenReturn(null);
//        when(responseSpec.bodyToMono(Void.class)).thenReturn(voidMono);
//        when(requestHeadersUriSpec.uri("/inventory/update")).thenReturn(requestHeadersSpec);
//        when(requestBodySpec.bodyValue(any(Inventory.class))).thenReturn(requestHeadersUriSpec);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Order> requestEntity = new HttpEntity<>(order, headers);
//        ResponseEntity<?> responseEntity = orderController.saveOrder(order);
//
//        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
//    }
//
//    @Test
//    public void saveOrderTest_noStockAvailable() {
//
//        when(response.block()).thenReturn(product);
//
//        ResponseEntity<?> responseEntity = orderController.saveOrder(order);
//
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertEquals("No stock available", responseEntity.getBody());
//    }
//}