package com.hardikit.inventoryimage.service;

import com.hardikit.inventoryimage.entity.Item;
import com.hardikit.inventoryimage.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item findById(Long id){
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Long id,Item item) {
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
            existingItem.setItemName(item.getItemName());
            existingItem.setPrice(item.getPrice());
            existingItem.setQuantity(item.getQuantity());
            existingItem.setStatus(item.getStatus());
        existingItem.setCategory(item.getCategory());
        Item updatedItem = itemRepository.save(existingItem);
        return updatedItem;
    }

    public List<Item> getAllItems(){
        List<Item> items = itemRepository.findAll();
        return items;
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> getStockItems() {
        List<Item> items = itemRepository.findByQuantityGreaterThan(0);
        return items;
    }

    public Item updateItemStock(Long id, Integer stock) {
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setQuantity(stock);
        Item updatedItem = itemRepository.save(existingItem);
        return updatedItem;
    }
    public Item updateItemStatus(Long id, String status) {
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setStatus(status);
        Item updatedItem = itemRepository.save(existingItem);
        return updatedItem;
    }

    public Item getStockItemById(Long id) {
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.getQuantity();
        return existingItem;
    }
}
