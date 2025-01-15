package com.purchaseorder.repository;

import com.purchaseorder.config.Constant;
import com.purchaseorder.model.Users;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private HikariDataSource dataSource;

    JdbcTemplate getTemplateDataSource() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

    @Override
    public List<Users> findAll() {
        String sql = "SELECT * FROM users";
        return getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(Users.class));
    }

    @Override
    public Users findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<Users> users = getTemplateDataSource().query(sql, new BeanPropertyRowMapper<>(Users.class), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Users save(Users user) {
        String sql = "INSERT INTO users (id, first_name, last_name, email, phone, created_by, updated_by, created_datetime, updated_datetime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        user.setId(Constant.generateUserId());
        getTemplateDataSource().update(sql, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getCreatedBy(), user.getUpdatedBy(), user.getCreatedDatetime(), user.getUpdatedDatetime());
        return user;
    }

    @Override
    public Users update(int id, Users user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone = ?, created_by = ?, updated_by = ?, created_datetime = ?, updated_datetime = ? WHERE id = ?";
        getTemplateDataSource().update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getCreatedBy(), user.getUpdatedBy(), user.getCreatedDatetime(), user.getUpdatedDatetime(), id);
        return user;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        getTemplateDataSource().update(sql, id);
    }
}
