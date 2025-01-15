package com.purchaseorder.repository;

import com.purchaseorder.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findAll();

    Item findById(int id);

    Item save(Item item);

    Item update(int id, Item item);

    void delete(int id);
}
