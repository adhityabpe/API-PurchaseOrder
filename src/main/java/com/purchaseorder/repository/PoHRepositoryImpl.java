package com.purchaseorder.repository;

import com.purchaseorder.config.Constant;
import com.purchaseorder.model.PoH;
import com.purchaseorder.repository.PoHRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoHRepositoryImpl implements PoHRepository {

    @Autowired
    private HikariDataSource dataSource;

    JdbcTemplate getTemplateDataSource() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

    @Override
    public List<PoH> findAll() {
        String sql = "SELECT * FROM po_h";
        return getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(PoH.class));
    }

    @Override
    public PoH findById(int id) {
        String sql = "SELECT * FROM po_h WHERE id = ?";
        List<PoH> poHs = getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(PoH.class), id);
        return poHs.isEmpty() ? null : poHs.get(0);
    }

    @Override
    public PoH save(PoH poH) {
        String sql = "INSERT INTO po_h (id, datetime, description, total_price, total_cost, created_by, updated_by, created_datetime, updated_datetime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        poH.setId(Constant.generatePoHId());
        getTemplateDataSource().update(sql, poH.getId(), poH.getDatetime(), poH.getDescription(), poH.getTotalPrice(), poH.getTotalCost(), poH.getCreatedBy(), poH.getUpdatedBy(), poH.getCreatedDatetime(), poH.getUpdatedDatetime());
        return poH;
    }

    @Override
    public PoH update(PoH poH) {
        String sql = "UPDATE po_h SET datetime = ?, description = ?, total_price = ?, total_cost = ?, created_by = ?, updated_by = ?, created_datetime = ?, updated_datetime = ? WHERE id = ?";
        getTemplateDataSource().update(sql, poH.getDatetime(), poH.getDescription(), poH.getTotalPrice(), poH.getTotalCost(), poH.getCreatedBy(), poH.getUpdatedBy(), poH.getCreatedDatetime(), poH.getUpdatedDatetime(), poH.getId());
        return poH;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM po_h WHERE id = ?";
        getTemplateDataSource().update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM po_h WHERE id = ?";
        Integer count = getTemplateDataSource().queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
