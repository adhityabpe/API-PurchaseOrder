package com.purchaseorder.repository;

import com.purchaseorder.config.Constant;
import com.purchaseorder.model.PoD;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoDRepositoryImpl implements PoDRepository {

    @Autowired
    private HikariDataSource dataSource;

    JdbcTemplate getTemplateDataSource() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

    @Override
    public List<PoD> findAll() {
        String sql = "SELECT * FROM po_d";
        return getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(PoD.class));
    }

    @Override
    public List<PoD> findByPoHId(int poHId) {
        String sql = "SELECT * FROM po_d WHERE poh_id = ?";
        return getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(PoD.class), poHId);
    }

    @Override
    public PoD findById(int id) {
        String sql = "SELECT * FROM po_d WHERE id = ?";
        List<PoD> poDs = getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(PoD.class), id);
        return poDs.isEmpty() ? null : poDs.get(0);
    }

    @Override
    public PoD save(PoD poD) {
        String sql = "INSERT INTO po_d (id, poh_id, item_id, item_qty, item_cost, item_price) VALUES (?, ?, ?, ?, ?, ?)";
        poD.setId(Constant.generatePoDId());
        getTemplateDataSource().update(sql, poD.getId(), poD.getPohId(), poD.getItemId(), poD.getItemQty(), poD.getItemCost(), poD.getItemPrice());
        return poD;
    }

    @Override
    public PoD update(PoD poD) {
        String sql = "UPDATE po_d SET poh_id = ?, item_id = ?, item_qty = ?, item_cost = ?, item_price = ? WHERE id = ?";
        getTemplateDataSource().update(sql, poD.getPohId(), poD.getItemId(), poD.getItemQty(), poD.getItemCost(), poD.getItemPrice(), poD.getId());
        return poD;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM po_d WHERE id = ?";
        getTemplateDataSource().update(sql, id);
    }

    @Override
    public void deleteByPoHId(int poHId) {
        String sql = "DELETE FROM po_d WHERE poh_id = ?";
        getTemplateDataSource().update(sql, poHId);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM po_d WHERE id = ?";
        Integer count = getTemplateDataSource().queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
