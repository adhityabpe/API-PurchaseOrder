package com.purchaseorder.repository;

import com.purchaseorder.model.Users;

import java.util.List;

public interface UsersRepository {
    List<Users> findAll();

    Users findById(int id);

    Users save(Users user);

    Users update(int id, Users user);

    void delete(int id);
}
