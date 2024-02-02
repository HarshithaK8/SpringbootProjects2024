package com.example.productservice.DAO;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product,Integer> {

    public List<Product> findAllByCategory(Category category);
    public Product findByProductName(String name);
}
