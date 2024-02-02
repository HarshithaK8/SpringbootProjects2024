package com.example.productservice.Service;

import com.example.productservice.model.Category;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> getAllProduct();
    public List<Product> getAllProductByCategory(Category category);
    public Product getProductById(Integer pid);
    public Product getProductByName(String name);
    public Product addProduct(Product product);
    public void deleteProduct(Integer productId);
}
