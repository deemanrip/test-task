package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Customer;

import java.util.List;

public interface CustomerDao {

    void save(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);

    List<Customer> getAll();
    Customer getById(Long id);
}
