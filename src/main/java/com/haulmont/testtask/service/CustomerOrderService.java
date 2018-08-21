package com.haulmont.testtask.service;

import com.haulmont.testtask.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {

    void createOrUpdateCustomerOrder(CustomerOrder order);
    void deleteCustomerOrder(CustomerOrder order);

    List<CustomerOrder> findAllCustomerOrders();
}