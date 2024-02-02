package com.example.categoryservice.Controller;

import com.example.categoryservice.Service.CategoryService;
import com.example.categoryservice.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.mockito.Mockito.when;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
    public class CategoryControllerTest {

        @Mock
        private CategoryService categoryService;

        @InjectMocks
        private CategoryController categoryController;

//        @BeforeEach
//        public void setup() {
//            categoryService = mock(CategoryService.class);
//            categoryController = new CategoryController(
//        }
        @Test
        public void testGetCategories() {
            List<Category> categories = new ArrayList<>();
            categories.add(new Category());
            when(categoryService.listCategories()).thenReturn(categories);

            ResponseEntity<List<Category>> responseEntity = categoryController.getCategoryById();

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(categories, responseEntity.getBody());
            verify(categoryService, times(1)).listCategories();
        }

            @Test
            void testGetCategoryById() {
                Optional<Category> category = Optional.of(new Category());
                when(categoryService.readCategory(1)).thenReturn(category);

                ResponseEntity<Optional<Category>> responseEntity = categoryController.getCategoryById(1);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals(category, responseEntity.getBody());
                verify(categoryService, times(1)).readCategory(1);
            }

            @Test
            void testCreateCategoryWithExistingName() {
                Category category = new Category();
                category.setCname("existingName");
                when(categoryService.readCategory(category.getCname())).thenReturn(new Category());

                ResponseEntity<?> responseEntity = categoryController.createCategory(category);

                assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
                verify(categoryService, times(1)).readCategory(category.getCname());
            }
        }
