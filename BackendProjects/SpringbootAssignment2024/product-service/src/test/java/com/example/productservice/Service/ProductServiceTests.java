package com.example.productservice.Service;

import com.example.productservice.DAO.ProductDAO;
import com.example.productservice.model.Category;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Price;
import com.example.productservice.model.Product;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {

    @Mock
    private ProductDAO productDAO;

    @InjectMocks
    private ProductServiceImpl productService;
    private Inventory inventory;

    private Price price;
    private Category category;

    private Product product;

    private List<Product> products;

        @Before
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
        }

    @Test
    public void get_all_product_test(){

        Mockito.when(productDAO.findAll()).thenReturn(products);

        // Method call
        List<Product> foundProducts = productService.getAllProduct();

        // Verification
        assertEquals(foundProducts.get(0).getProductName(), product.getProductName());
        Mockito.verify(productDAO, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(productDAO);
    }

    @Test
    public void get_one_by_id_test(){
        // Data preparation
        Mockito.when(productDAO.getOne(product.getProductId())).thenReturn(product);

        // Method call
        Product found = productService.getProductById(product.getProductId());

        // Verification
        assertEquals(found.getProductId(), product.getProductId());
        Mockito.verify(productDAO, Mockito.times(1)).getOne(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(productDAO);
    }

    @Test
    public void get_all_product_by_category_test(){
        // Data preparation
        Mockito.when(productDAO.findAllByCategory(category)).thenReturn(products);

        //Method call
        List<Product> foundProducts = productService.getAllProductByCategory(category);

        //Verification
        assertEquals(products.get(0).getCategory(), product.getCategory());
        assertEquals(products.get(0).getProductName(), product.getProductName());
        Mockito.verify(productDAO, Mockito.times(1)).findAllByCategory(Mockito.any());
        Mockito.verifyNoMoreInteractions(productDAO);
    }

    @Test
    public void testAddProduct() {
            Mockito.when(productDAO.save(product)).thenReturn(product);
            Product foundProduct = productService.addProduct(product);
            assertEquals(foundProduct,product);
                Mockito.verify(productDAO, Mockito.times(1)).save(Mockito.any());
                Mockito.verifyNoMoreInteractions(productDAO);

    }

    @Test
    public void testDeleteProduct() {
        Mockito.doNothing().when(productDAO).deleteById(product.getProductId());
        productService.deleteProduct(product.getProductId());
        Mockito.verify(productDAO, Mockito.times(1)).deleteById(Mockito.any());
        Mockito.verifyNoMoreInteractions(productDAO);

    }

    @Test
    public void get_all_products_by_name_test(){
        // Data preparation
        Mockito.when(productDAO.findByProductName(product.getProductName())).thenReturn(product);

        //Method call
        Product foundProduct = productService.getProductByName(product.getProductName());

        //Verification
        assertEquals(foundProduct.getProductName(), product.getProductName());
        Mockito.verify(productDAO, Mockito.times(1)).findByProductName(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(productDAO);
    }

}
