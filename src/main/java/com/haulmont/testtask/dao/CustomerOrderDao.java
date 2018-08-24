package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderDao {

    void save(CustomerOrder order);
    void update(CustomerOrder order);
    void delete(CustomerOrder order);

    List<CustomerOrder> getAll();
    CustomerOrder getById(Long id);
}
