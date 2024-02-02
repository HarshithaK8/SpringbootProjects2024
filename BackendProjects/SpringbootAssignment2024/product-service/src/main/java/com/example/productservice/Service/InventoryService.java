package com.example.productservice.Service;

import com.example.productservice.model.Inventory;
import org.springframework.stereotype.Service;

@Service
public interface InventoryService {
    public Inventory addInventory(Inventory inventory);

    public void deleteInventory(Integer id);

    public Inventory getInventoryById(Integer id);

}
