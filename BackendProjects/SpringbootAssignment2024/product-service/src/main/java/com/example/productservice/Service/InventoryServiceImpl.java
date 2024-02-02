package com.example.productservice.Service;

import com.example.productservice.DAO.InventoryDAO;
import com.example.productservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    InventoryDAO inventoryDAO;

    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryDAO.save(inventory);
    }

    @Override
    public void deleteInventory(Integer id) {
        inventoryDAO.deleteById(id);
    }

    @Override
    public Inventory getInventoryById(Integer id) {
        return inventoryDAO.getOne(id);
    }
}
