package com.hardikit.inventoryimage.service;

import com.hardikit.inventoryimage.entity.Category;
import com.hardikit.inventoryimage.entity.Item;
import com.hardikit.inventoryimage.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }


    public Category updateItem(Long id, Category category) {
        Category existingItem = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        if(category.getCategoryName() != null)
            existingItem.setCategoryName(category.getCategoryName());
        Category updatedItem = categoryRepository.save(existingItem);
        return updatedItem;
    }


    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return  categories;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
