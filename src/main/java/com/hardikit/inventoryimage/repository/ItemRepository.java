package com.hardikit.inventoryimage.repository;

import com.hardikit.inventoryimage.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByQuantityGreaterThan(int i);
}
