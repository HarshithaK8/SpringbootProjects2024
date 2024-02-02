package com.example.categoryservice.DAO;

import com.example.categoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<Category,Integer> {

    Category findByCname(String categoryName);
}
