package com.example.productservice.controllers;

import com.example.productservice.Service.InventoryService;
import com.example.productservice.Service.ProductService;
import com.example.productservice.model.Category;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private String message = "No items in the inventory";

    @GetMapping (value = "/products")
    public ResponseEntity<?> getAllProducts(){
        List<Product> products =  productService.getAllProduct();
        if(!products.isEmpty()) {
        	return new ResponseEntity<List<Product>>(
        			products,
        			HttpStatus.OK);
        }
        return new ResponseEntity<>(message,
                HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/productsByCategory/{category-id}")
    public ResponseEntity<?> getAllProductByCategory(@PathVariable ("category-id") int id){
        RestTemplate restTemplate = new RestTemplate();
        Category category = restTemplate.getForObject("http://localhost:8810/category/"+id,Category.class);
        List<Product> products = productService.getAllProductByCategory(category);
        if(!products.isEmpty()) {
        	return new ResponseEntity<List<Product>>(
                    products,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(message,
                HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/productsById/{product-id}")
    public ResponseEntity<?> getOneProductById(@PathVariable ("product-id") int pid){
        Product product =  productService.getProductById(pid);
        if(product != null) {
        	return new ResponseEntity<>(
                    product,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(message,
                HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/productByName/{product-name}")
    public ResponseEntity<?> getAllProductsByName(@PathVariable ("product-name") String name){
        Product product =  productService.getProductByName(name);
        if(product!=null) {
        	return new ResponseEntity<>(
                    product,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(message,
                HttpStatus.NOT_FOUND);
    }
}
