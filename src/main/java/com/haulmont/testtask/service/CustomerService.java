package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Customer;

import java.util.List;

public interface CustomerService {

    void createCustomer(Customer customer);
    List<Customer> findAllCustomers();
}
