package com.example.productservice.Controller;

import com.example.productservice.Service.InventoryService;
import com.example.productservice.controllers.InventoryController;
import com.example.productservice.model.Inventory;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    private Inventory inventory;

//    @BeforeEach
//    public void setUp() {
//        inventory = new Inventory();
//        inventory.setId(1);
//        inventory.setAvailable(50);
//        inventory.setReserved(50);
//        inventory.setTotal(100);
//        when(inventoryService.getInventoryById(1)).thenReturn(inventory);
//        when(inventoryService.addInventory(inventory)).thenReturn(inventory);
//    }

    @Test
    public void testGetInventoryById() {

        inventory = new Inventory();
        inventory.setId(1);
        inventory.setAvailable(50);
        inventory.setReserved(50);
        inventory.setTotal(100);
        when(inventoryService.getInventoryById(1)).thenReturn(inventory);

        ResponseEntity<?> responseEntity = inventoryController.getInventoryById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(inventory, responseEntity.getBody());

        verify(inventoryService, times(1)).getInventoryById(1);
    }

    @Test
    public void testGetInventoryByIdNotFound() {
        when(inventoryService.getInventoryById(2)).thenReturn(null);

        ResponseEntity<String> responseEntity = (ResponseEntity<String>) inventoryController.getInventoryById(2);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Inventory for the given id does not exist", responseEntity.getBody());

        verify(inventoryService, times(1)).getInventoryById(2);
    }

    @Test
    public void testUpdateInventory() {
        Inventory updatedInventory = new Inventory();
        updatedInventory.setId(1);
        updatedInventory.setAvailable(50);
        updatedInventory.setReserved(50);
        updatedInventory.setTotal(100);

        when(inventoryService.addInventory(updatedInventory)).thenReturn(updatedInventory);

        ResponseEntity<?> responseEntity = inventoryController.updateInventory(updatedInventory);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedInventory, responseEntity.getBody());

        verify(inventoryService, times(1)).addInventory(updatedInventory);
    }

    @Test
    public void testUpdateInventoryNotFound() {
        Inventory updatedInventory = new Inventory();
        updatedInventory.setId(1);
        updatedInventory.setAvailable(50);
        updatedInventory.setReserved(50);
        updatedInventory.setTotal(100);

        when(inventoryService.addInventory(updatedInventory)).thenReturn(null);

        ResponseEntity<?> responseEntity = inventoryController.updateInventory(updatedInventory);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Inventory you are trying to update does not exist", responseEntity.getBody());

        verify(inventoryService, times(1)).addInventory(updatedInventory);
    }
}
