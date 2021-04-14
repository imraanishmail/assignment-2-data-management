package com.ishmail.inventory;

import com.ishmail.entity.Inventory;

import java.util.List;

public interface InventoryService {
    // methods that will be implemented in InventoryServiceImpl
    void clearList();
    List<Inventory> getInventoryList();
    void addToInventory(Inventory inventory);
    void removeFromInventory(Inventory inventory);
}
