package com.hardikit.inventoryimage.controller;

import com.hardikit.inventoryimage.entity.Category;
import com.hardikit.inventoryimage.entity.Item;
import com.hardikit.inventoryimage.service.CategoryService;
import com.hardikit.inventoryimage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createItem(@RequestBody Category category){
        Category category1 =  categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateItem(@PathVariable Long id, @RequestBody Category category){
        Category updatedCategory = categoryService.updateItem(id,category);
        return ResponseEntity.ok(updatedCategory);
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        categoryService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
