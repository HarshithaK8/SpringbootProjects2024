package com.example.productservice.Service;

import com.example.productservice.DAO.InventoryDAO;
import com.example.productservice.DAO.ProductDAO;
import com.example.productservice.model.Category;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private InventoryDAO inventoryDAO;

    @Override
    public List<Product> getAllProduct() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> getAllProductByCategory(Category category) {
        return productDAO.findAllByCategory(category);
    }

    @Override
    public Product getProductById(Integer id) {
        return productDAO.getOne(id);
    }

    @Override
    public Product getProductByName(String name) {
        return productDAO.findByProductName(name);
    }

    @Override
    public Product addProduct(Product product) {
        return productDAO.save(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDAO.deleteById(productId);
    }
}

