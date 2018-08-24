package com.haulmont.testtask.service;

import com.haulmont.testtask.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {

    void createCustomerOrder(CustomerOrder order);
    void updateCustomerOrder(CustomerOrder order);
    void deleteCustomerOrder(Long id);

    List<CustomerOrder> getAllOrders();
}