package com.example.categoryservice.Service;

import com.example.categoryservice.DAO.CategoryDAO;
import com.example.categoryservice.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public Category createCategory(Category category) {
        return categoryDAO.save(category);
    }

    public List<Category> listCategories() {
        return categoryDAO.findAll();
    }

    public Category readCategory(String categoryName) {
        return categoryDAO.findByCname(categoryName);
    }

    public Optional<Category> readCategoryById(Integer id) {
        return categoryDAO.findById(id);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryDAO.findById(categoryId);
    }

    public void updateCategory(Integer categoryID, Category newCategory) {
        Category category = categoryDAO.findById(categoryID).get();
        category.setCname(newCategory.getCname());
        category.setCdesc(newCategory.getCdesc());
        category.setImageurl(newCategory.getImageurl());
        categoryDAO.save(category);
    }
}
