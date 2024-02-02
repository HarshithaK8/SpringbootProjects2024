package com.example.productservice.controllers;

import com.example.productservice.Service.InventoryService;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable("id") int inventoryId){
        Inventory inventory = inventoryService.getInventoryById(inventoryId);
        if(inventory!=null) {
            return new ResponseEntity<Inventory>(
                    inventory,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Inventory for the given id does not exist",
                HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateInventory(@RequestBody Inventory inventory){
        Inventory updatedInvetory = inventoryService.addInventory(inventory);
        if(updatedInvetory!=null) {
            return new ResponseEntity<Inventory>(
                    updatedInvetory,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Inventory you are trying to update does not exist",
                HttpStatus.NOT_FOUND);
    }
}
