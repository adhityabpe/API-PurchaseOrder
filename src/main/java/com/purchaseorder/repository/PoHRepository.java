package com.purchaseorder.repository;

import com.purchaseorder.model.PoH;

import java.util.List;

public interface PoHRepository {
    List<PoH> findAll();

    PoH findById(int id);

    PoH save(PoH poH);

    PoH update(PoH poH);

    void deleteById(int id);

    boolean existsById(int id);
}
