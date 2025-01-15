package com.purchaseorder.repository;

import com.purchaseorder.config.Constant;
import com.purchaseorder.model.Item;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private HikariDataSource dataSource;

    JdbcTemplate getTemplateDataSource() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

    @Override
    public List<Item> findAll() {
        String sql = "SELECT * FROM items";
        return getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(Item.class));
    }

    @Override
    public Item findById(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        List<Item> items = getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(Item.class), id);
        return items.isEmpty() ? null : items.get(0);
    }

    @Override
    public Item save(Item item) {
        String sql = "INSERT INTO items (id, name, description, price, cost, created_by, updated_by, created_datetime, updated_datetime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        item.setId(Constant.generateItemId());
        getTemplateDataSource().update(sql, item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getCost(), item.getCreatedBy(), item.getUpdatedBy(), item.getCreatedDatetime(), item.getUpdatedDatetime());
        return item;
    }

    @Override
    public Item update(int id, Item item) {
        String sql = "UPDATE items SET name = ?, description = ?, price = ?, cost = ?, created_by = ?, updated_by = ?, created_datetime = ?, updated_datetime = ? WHERE id = ?";
        getTemplateDataSource().update(sql, item.getName(), item.getDescription(), item.getPrice(), item.getCost(), item.getCreatedBy(), item.getUpdatedBy(), item.getCreatedDatetime(), item.getUpdatedDatetime(), id);
        return item;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM items WHERE id = ?";
        getTemplateDataSource().update(sql, id);
    }
}
