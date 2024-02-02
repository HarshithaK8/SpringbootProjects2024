package com.example.inventoryservice.DAO;

import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryDAO extends JpaRepository<Inventory,Integer> {
}
