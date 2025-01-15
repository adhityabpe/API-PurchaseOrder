package com.purchaseorder.repository;

import com.purchaseorder.model.PoD;

import java.util.List;

public interface PoDRepository {
    List<PoD> findAll();

    List<PoD> findByPoHId(int poHId);

    PoD findById(int id);

    PoD save(PoD poD);

    PoD update(PoD poD);

    void deleteById(int id);

    void deleteByPoHId(int poHId);

    boolean existsById(int id);
}
