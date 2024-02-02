package com.example.productservice.controllers;

import com.example.productservice.Service.InventoryService;
import com.example.productservice.Service.ProductService;
import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

	@Autowired
	private InventoryService inventoryService;

    @PostMapping(value = "/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
    	if(product != null) {
    		try {
				inventoryService.addInventory(product.getInventory());
				product.setProductId(product.getInventory().getId());
    			productService.addProduct(product);
    	        return new ResponseEntity<>(
						product,
						HttpStatus.CREATED);
    		}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
    	}
    	return new ResponseEntity<>(
				HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id){
    	Product product = productService.getProductById(id);
    	if(product != null) {
    		try {
				inventoryService.deleteInventory(id);
    			productService.deleteProduct(id);
    	        return new ResponseEntity<>(
						HttpStatus.OK);
    		}catch (Exception e) {
				e.printStackTrace();
    	        return new ResponseEntity<>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
