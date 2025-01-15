package com.purchaseorder.service;

import com.purchaseorder.model.Item;
import com.purchaseorder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(int id) {
        return itemRepository.findById(id);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item update(int id, Item item) {
        Item existingItem = itemRepository.findById(id);
        if (existingItem == null) {
            return null;
        }
        item.setId(id);
        return itemRepository.update(id, item);
    }

    public void delete(int id) {
        Item existingItem = itemRepository.findById(id);
        if (existingItem != null) {
            itemRepository.delete(id);
        }
    }
}
