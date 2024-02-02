package com.example.productservice.Service;

import com.example.productservice.DAO.InventoryDAO;
import com.example.productservice.model.Category;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Price;
import com.example.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTests {

    @Mock
    private InventoryDAO inventoryDAO;
    @InjectMocks
    private InventoryServiceImpl inventoryService;
    private Inventory inventory;

    @Before
    public void setUp() {

        inventory = new Inventory();
        inventory.setId(1);
        inventory.setAvailable(50);
        inventory.setReserved(50);
        inventory.setTotal(100);
    }

    @Test
    public void testAddInventory() {

        Mockito.when(inventoryDAO.save(inventory)).thenReturn(inventory);

        // Method call
        Inventory foundInventory = inventoryService.addInventory(inventory);

        // Verification
        assertEquals(foundInventory, inventory);
        Mockito.verify(inventoryDAO, Mockito.times(1)).save(inventory);
        Mockito.verifyNoMoreInteractions(inventoryDAO);
    }

    @Test
    public void testDeleteInventory() {
        // Data preparation
        Mockito.doNothing().when(inventoryDAO).deleteById(inventory.getId());

        inventoryService.deleteInventory(inventory.getId());
//
//        //verification
//        Mockito.verifyNoMoreInteractions(inventoryDAO);
    }

    @Test
    public void testgetInventoryById() {
        // Data preparation
        Mockito.when(inventoryDAO.getOne(inventory.getId())).thenReturn(inventory);

        //Method call
        Inventory foundInventory = inventoryService.getInventoryById(inventory.getId());

        //Verification
        assertEquals(foundInventory, inventory);
    }
}