package com.ishmail.inventory;

import com.ishmail.entity.Inventory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless // does not preserve any state and not unique to the user
@Remote(InventoryService.class) // //Remote - tells the EJB container that this bean may be used by another project
public class InventoryServiceImpl implements InventoryService {

    @PersistenceContext // translates java terms to SQL database terms
    private EntityManager em; // communicates with Persistence Context, done via @PersistenceContext

    // clear all items from inventory
    @Override
    public void clearList() {
        Query deleteFromInventory = em.createNamedQuery("Inventory.clearAll", Inventory.class);
        deleteFromInventory.executeUpdate();
    }

    // get inventory and sort in reverse order
    @Override
    public List<Inventory> getInventoryList() {
        // createNamedQuery specifies name and class where query is located and will avoid having to cast
        List<Inventory> inventoryList = em.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();
                return inventoryList
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    // add item to inventory
    @Override
    public void addToInventory(Inventory inventory) {
        em.persist(inventory);
    }

    // will remove a single inventory item
    @Override
    public void removeFromInventory(Inventory inventory) {
        Inventory correspondingItem = em.createNamedQuery("Inventory.getByName", Inventory.class)
        .setParameter("name", inventory.getName())
        .getSingleResult();
        em.remove(correspondingItem);

    }
}
