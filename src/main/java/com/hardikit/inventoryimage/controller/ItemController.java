package com.hardikit.inventoryimage.controller;

import com.hardikit.inventoryimage.entity.Item;
import com.hardikit.inventoryimage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item savedItem =  itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id,@RequestBody Item item){
        Item updatedItem = itemService.updateItem(id,item);
        return ResponseEntity.ok(updatedItem);
    }

    @PutMapping("/updateStock/{id}")
    public ResponseEntity<Item> updateItemStock(@PathVariable Long id,@RequestParam("stock") Integer stock ){
        Item updatedItem = itemService.updateItemStock(id,stock);
        return ResponseEntity.ok(updatedItem);
    }

    @PutMapping("/updateItemStatus/{id}")
    public ResponseEntity<Item> updateItemStatus(@PathVariable Long id,@RequestParam("status") String status ){
        Item updatedItem = itemService.updateItemStatus(id,status);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        itemService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/stock")
    public ResponseEntity<List<Item>> getStockItems(){
        List<Item> items = itemService.getStockItems();
        return ResponseEntity.ok(items);
    }
    @GetMapping("/stock/{id}")
    public ResponseEntity<Item> getStockItemById(@PathVariable Long id){
        Item items = itemService.getStockItemById(id);
        return ResponseEntity.ok(items);
    }
}
