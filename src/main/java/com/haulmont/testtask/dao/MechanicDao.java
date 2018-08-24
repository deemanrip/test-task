package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Mechanic;

import java.util.List;

public interface MechanicDao {

    void save(Mechanic mechanic);
    void update(Mechanic mechanic);
    void delete(Mechanic mechanic);

    List<Mechanic> getAll();
    Mechanic getById(Long id);
}
